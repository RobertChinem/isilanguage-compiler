package compiler.ast;

public class CommandAssignment extends AbstractCommand {
    private String id;
	private String expression;

    public CommandAssignment(String id, String expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public String generateJavaCode() {
        return String.format("%s = %s;", id, expression);
    }
}
