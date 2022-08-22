const { spawn } = require("child_process");
const { v4: uuidv4 } = require("uuid");
const fs = require("fs");
const path = require("path");

async function buildIsiLanguageTranspiler() {
    console.log("Building IsiLanguageTranspiler...");
    const step_1 = await new Promise((resolve, reject) => {
        const result = spawn("java", [
            "-cp",
            ".:transpiler/antlr-4.7.1-complete.jar",
            "org.antlr.v4.Tool",
            "transpiler/IsiLang.g4",
            "-package",
            "compiler.parser",
            "-o",
            "transpiler/compiler/parser",
            "-Xexact-output-dir"
        ], { shell: true });
        result.stdout.on("data", (data) => { resolve(data.toString()); });
        result.stderr.on("data", (data) => { resolve(data.toString()); });
        result.on("exit", (data) => { resolve(data.toString()); });
    })
    const step_2 = await new Promise((resolve, reject) => {
        const result = spawn("javac", [
            "-cp",
            ".:transpiler/antlr-4.7.1-complete.jar",
            "transpiler/compiler/**/*.java"
        ], { shell: true });
        result.stdout.on("data", (data) => { resolve(data.toString()); });
        result.stderr.on("data", (data) => { resolve(data.toString()); });
        result.on("exit", (data) => { resolve(data.toString()); });
    })

    console.log("Done!");
    console.log("step_1: ", step_1);
    console.log("step_2: ", step_2);
}


async function generateJavaCode(codeId) {
    const timeout = 8;
    try {
        const output = await new Promise((resolve, reject) => {
            const execution = spawn("java", [
                "-cp",
                ".:transpiler/antlr-4.7.1-complete.jar:transpiler",
                "compiler/main/MainClass",
                path.join(__dirname, `tmp/codes/${codeId}`),
                path.join(__dirname, `tmp/codes/${codeId}.java`)
            ], { shell: true });

            let outputString = "",
                errorString = "";

            execution.stdin.on("error", (...args) => {
                console.log("stdin err", args);
            });

            execution.stdout.on("data", (data) => {
                outputString += data.toString();
            });

            execution.stderr.on("data", (data) => {
                errorString += data.toString();
            });

            execution.on("exit", () => {
                if (errorString) reject(errorString);
                resolve(outputString);
            });

            setTimeout(() => {
                reject(
                    `Error: Timed Out`
                );
            }, timeout * 1000);
        });

        const javaCode = fs.readFileSync(path.join(__dirname, `tmp/codes/${codeId}.java`), "utf8");
        return {
            success: true,
            output,
            code: javaCode
        };
    } catch (error) {
        return {
            success: false,
            error,
        };
    }
}


async function executeJava(codeId, input) {
    const timeout = 8;
    try {
        const output = await new Promise((resolve, reject) => {
            const codeExec = spawn("java", [
                "-Dfile.encoding=UTF-8",
                path.join(__dirname, "tmp/codes", codeId)
            ]);

            let outputString = "",
                errorString = "";

            if (input) {
                codeExec.stdin.write(input);
                codeExec.stdin.end();
            }

            codeExec.stdin.on("error", (...args) => {
                console.log("stdin err", args);
            });

            codeExec.stdout.on("data", (data) => {
                outputString += data.toString();
            });

            codeExec.stderr.on("data", (data) => {
                errorString += data.toString();
            });

            codeExec.on("exit", () => {
                if (errorString) reject(errorString);
                resolve(outputString);
            });

            setTimeout(() => {
                reject(
                    "Error: Timed Out"
                );
            }, timeout * 1000);
        });

        return {
            success: true,
            output,
        };
    } catch (error) {
        console.log(error);
        return {
            success: false,
            error,
        };
    }
}

function saveCode(code, language) {
    const id = uuidv4();
    const filename = `${id}.${language}`;
    fs.writeFileSync(path.join(__dirname, "tmp/codes", filename), code);
    return filename;
}

module.exports = {
    buildIsiLanguageTranspiler,
    generateJavaCode,
    executeJava,
    saveCode
}