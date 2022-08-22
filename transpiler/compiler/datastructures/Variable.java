package compiler.datastructures;

public class Variable extends Symbol {
    public static final int INT     =   0;
    public static final int DOUBLE  =   1;
	public static final int TEXT    =   2;
    

    private int type;
    private String value;

    public Variable(String name, int type, String value) {
        super(name);
        this.type = type;
        this.value = value;
    }

    @Override
    public String generateJavaCode() {
        switch(type){
            case INT:
                return String.format("int %s = 0;", name);
            case DOUBLE:
                return String.format("double %s = 0.0;", name);
            case TEXT:
                return String.format("String %s = \"\";", name);
            default:
                return "";
        }
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
