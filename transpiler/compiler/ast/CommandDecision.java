package compiler.ast;

import java.util.ArrayList;

public class CommandDecision extends AbstractCommand {

    private String condition;
	private ArrayList<AbstractCommand> listTrue;
	private ArrayList<AbstractCommand> listFalse;
    
    public CommandDecision(String condition, ArrayList<AbstractCommand> listTrue, ArrayList<AbstractCommand> listFalse) {
        this.condition = condition;
        this.listTrue = listTrue;
        this.listFalse = listFalse;
    }
    
    @Override
    public String generateJavaCode() {
        String code = "";
        code += generateIfBlock();
        code += generateElseBlock();
        return code;
    }

    private String generateIfBlock() {
        String code = String.format("if(%s){\n", condition);
        for (AbstractCommand cmd: listTrue) {
			code += cmd.generateJavaCode() + '\n';
		}
        code += "}\n";
        return code;
    }


    private String generateElseBlock() {
        if(listFalse == null || listFalse.size() == 0) {
            return "";
        }

        String code = "else {\n";
        for (AbstractCommand cmd: listFalse) {
            code += cmd.generateJavaCode() + '\n';
        }
        code += "}\n";
        return code;
    }
}
