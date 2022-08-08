package compiler.ast;

import compiler.datastructures.Variable;

public class CommandRead extends AbstractCommand {
    private String id;
    private Variable variable;
    
    public CommandRead(String id, Variable variable) {
        this.id = id;
        this.variable = variable;
    }
    
    @Override
    public String generateJavaCode() {
        switch(variable.getType()){
            case Variable.NUMBER:
                return String.format("%s = _key.nextInt();", id);
            case Variable.TEXT:
                return String.format("%s = _key.nextLine();", id);
            default:
                return "";
        }
    }
}
