package compiler.ast;

import java.util.ArrayList;

public class CommandSwitchCase extends AbstractCommand {
    private String condition;
    private ArrayList<AbstractCommand> list;
    
    public CommandSwitchCase(String condition, ArrayList<AbstractCommand> list) {
        this.condition = condition;
        this.list = list;
    }
    
    @Override
    public String generateJavaCode() {
        String code = String.format("case %s:\n", condition);
        for (AbstractCommand cmd: list) {
            code += cmd.generateJavaCode() + '\n';
        }
        code += "break;\n";
        return code;
    }
}
