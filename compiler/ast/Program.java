package compiler.ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;

import compiler.datastructures.Symbol;
import compiler.datastructures.SymbolTable;

public class Program {
    private HashSet<String> usedVariables = new HashSet<String>();
    private SymbolTable symbolTable;
    private ArrayList<AbstractCommand> commands;
    private String programName;

    public void generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("import java.util.Scanner;\n");
        str.append("import java.lang.Math;\n");
        str.append("public class MainClass{ \n");
        str.append("  public static void main(String args[]){\n ");
        str.append("      Scanner _key = new Scanner(System.in);\n");
        
        for(Symbol symbol: symbolTable.getValues()){
            str.append(symbol.generateJavaCode()+"\n");
        }
        
        for(AbstractCommand command: commands){
            str.append(command.generateJavaCode()+"\n");
        }
        
        str.append("  }");
        str.append("}");

        for(String variable: symbolTable.getKeys()){
            if(!usedVariables.contains(variable)){
                System.out.println("Warning: unused variable: " + variable);
            }
        }

        System.out.println("Build completed");

        try {
            FileWriter fr = new FileWriter(new File("MainClass.java"));
            fr.write(str.toString());
            fr.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<AbstractCommand> commands) {
        this.commands = commands;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public HashSet<String> getUsedVariables() {
        return usedVariables;
    }

    public void setUsedVariables(HashSet<String> usedVariables) {
        this.usedVariables = usedVariables;
    }
}
