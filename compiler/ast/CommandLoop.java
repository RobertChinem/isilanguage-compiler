package compiler.ast;

import java.util.ArrayList;

public class CommandLoop extends AbstractCommand {
    private String condition;
	private ArrayList<AbstractCommand> listTrue;

    public CommandLoop(String condition, ArrayList<AbstractCommand> listTrue) {
        this.condition = condition;
        this.listTrue = listTrue;
    }

    @Override
    public String generateJavaCode() {
        String code = String.format("while(%s){\n", condition);
        for (AbstractCommand command : listTrue) {
            code += command.generateJavaCode() + "\n";
        }
        code += "}\n";
        return code;
    }
}
