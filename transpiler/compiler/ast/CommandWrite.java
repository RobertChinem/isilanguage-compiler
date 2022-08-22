package compiler.ast;

public class CommandWrite extends AbstractCommand {
    private String parameter;

    public CommandWrite(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String generateJavaCode() {
        return String.format("System.out.println(%s);", parameter);
    }
}
