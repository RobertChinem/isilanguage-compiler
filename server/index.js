const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const { buildIsiLanguageTranspiler, generateJavaCode, saveCode, executeJava } = require("./util");
const path = require("path");
const app = express();
const port = process.env.PORT || 3000;
const fs = require("fs");
const prettier = require("prettier");

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cors());


app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "html/index.html"));
})

app.get("/api/example", (req, res) => {
    const file = fs.readFileSync(path.join(__dirname, "tmp/codes/example.isi"));
    const codeExample = file.toString();
    res.send({
        codeExample
    })
})


app.post("/api/run/java", async (req, res) => {
    const { code, input = "" } = req.body;

    if (code === undefined || code.trim() === "") {
        res.status(400).send("Code is required");
        return;
    }

    const codeId = saveCode(code, "java");
    res.send(await executeJava(codeId, input));
})

app.post("/api/compileIsiLang", async (req, res) => {
    const { code } = req.body;

    if (code === undefined || code.trim() === "") {
        res.status(400).send("Code is required");
        return;
    }
    const codeId = saveCode(code, "isi");
    const result = await generateJavaCode(codeId);

    if(result.code){
        result.code = prettier.format(result.code, {
            parser: "java",
            tabWidth: 4
        })
    }

    res.send(result);
})

app.listen(port, async () => {
    await buildIsiLanguageTranspiler();
    console.log(`Server is running on port: ${port}`);
})