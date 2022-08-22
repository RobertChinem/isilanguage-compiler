package compiler.ast;

import java.util.ArrayList;

public class CommandSwitch extends AbstractCommand {
    private String variableName;
    private ArrayList<CommandSwitchCase> list;

    public CommandSwitch(String variableName, ArrayList<CommandSwitchCase> list) {
        this.variableName = variableName;
        this.list = list;
    }

    @Override
    public String generateJavaCode() {
        String code = String.format("switch(%s) {\n", variableName);
        for (AbstractCommand cmd: list) {
            code += cmd.generateJavaCode() + '\n';
        }
        code += "}\n";
        return code;
    }
}
