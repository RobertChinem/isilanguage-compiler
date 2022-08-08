grammar IsiLang;

@header{
    import java.util.HashSet;
    import java.util.ArrayList;
    import java.util.Stack;
    import compiler.exceptions.SemanticException;
    import compiler.datastructures.SymbolTable;
    import compiler.datastructures.Symbol;
    import compiler.datastructures.Variable;
    import compiler.ast.CommandRead;
    import compiler.ast.CommandWrite;
    import compiler.ast.CommandDecision;
    import compiler.ast.AbstractCommand;
    import compiler.ast.CommandAssignment;
    import compiler.ast.CommandSwitchCase;
    import compiler.ast.CommandSwitch;
    import compiler.ast.CommandLoop;
    import compiler.ast.Program;
}

@members{
    private int _variableType;
    private String _variableName;
    private String _variableValue;
    private SymbolTable _symbolTable = new SymbolTable();
    private HashSet<String> _usedVariables = new HashSet<String>();
    private Program program = new Program();
    private ArrayList<AbstractCommand> scopes;
    private Stack<ArrayList<AbstractCommand>> scopeStack = new Stack<ArrayList<AbstractCommand>>();
    private Stack<String> _exprDecisionStack = new Stack<String>();
    private Stack<ArrayList<CommandSwitchCase>> _switchCaseStack = new Stack<ArrayList<CommandSwitchCase>>();
    private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
    private ArrayList<AbstractCommand> listTrue;
	private ArrayList<AbstractCommand> listFalse;

    public void checkID(String id) {
        if(!_symbolTable.contains(id)) {
            throw new SemanticException("Undeclared variable: " + id);
        }
    }

    public void generateCode(){
        program.generateTarget();
    }

    public boolean checkTypes(String targetID, String ID){
        boolean targetIsNum = hasType(targetID, Variable.INT) || hasType(targetID, Variable.DOUBLE);
        boolean id = hasType(ID, Variable.INT) || hasType(ID, Variable.DOUBLE);

        if(targetIsNum && id){
            return true;
        }

        if(hasType(targetID, Variable.TEXT) && hasType(ID, Variable.TEXT)){
            return true;
        }

        return false;
    }

    public boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public String getLastOperator(String expr){
        int i = expr.length() - 1;
        String lastOperator = "";   
        while(i >= 0 && isOperator(expr.charAt(i))){
            lastOperator = expr.charAt(i) + lastOperator;
            i--;
        }
        return lastOperator;
    }

    public String replacePower(String exprContent, String currentId){
        int i = exprContent.length() - 3;
        String aux = "";
        while(i >= 0 && !isOperator(exprContent.charAt(i))){
            aux = exprContent.charAt(i) + aux;
            i--;
        }
        return exprContent.replace(aux+"**", "") + String.format("Math.pow((double)%s, (double)%s)", aux, currentId);
    }

    public boolean hasType(String ID, int type){
        return ((Variable)_symbolTable.get(ID)).getType() == type;
    }
}

prog    :   'programa' declara bloco 'fimprog.' {
                program.setSymbolTable(_symbolTable);
                program.setCommands(scopeStack.pop());
                program.setUsedVariables(_usedVariables);
            }
        ;


declara :   'declare' criacaoVariavel (',' criacaoVariavel)* '.'
        ;


criacaoVariavel :   tipo
                    Id {
                        _variableName = _input.LT(-1).getText();
                        _variableValue = null;
                        if(_symbolTable.contains(_variableName)){
                            throw new SemanticException("Variable " + _variableName + " already declared");
                        }
                        _symbolTable.add(new Variable(_variableName, _variableType, _variableValue));
                    }
                ;


bloco   :   {
                scopeStack.push(new ArrayList<AbstractCommand>());
            }
            (cmd)+
        ;


cmd     :   cmdLeitura
        |   cmdEscrita
        |   cmdExpr
        |   cmdIf
        |   cmdRepeticao
        |   cmdEscolha
        ;


cmdLeitura  :   'leia' '(' 
                Id  {   _readID = _input.LT(-1).getText();  }
                ')' '.'   {
                    checkID(_readID);
                    _usedVariables.add(_readID);
                    CommandRead cmd = new CommandRead(_readID, (Variable)_symbolTable.get(_readID));
                    scopeStack.peek().add(cmd);
                }
            ;


cmdEscrita  :   'escreva' '(' 
                Texto   {   _exprContent = _input.LT(-1).getText(); }
                ')' '.' {
                    scopeStack.peek().add(new CommandWrite(_exprContent));
                }
            |   'escreva' '(' 
                Id  {   _readID = _input.LT(-1).getText();  }
                ')' '.' {
                    checkID(_readID);
                    scopeStack.peek().add(new CommandWrite(_readID));
                }
            ;


cmdIf   :   'se'    {
                _exprID = null;
                _exprContent = "";
            }
            '(' 
            expr   
            ')' 
            'entao' {
                _exprDecisionStack.push(_exprContent);
                scopeStack.push(new ArrayList<AbstractCommand>()); 
                listTrue = null;
                listFalse = null;
            }
            '{' cmd+ '}'
            (
                'senao'
                {   scopeStack.push(new ArrayList<AbstractCommand>());  }
                '{' cmd+ '}'
                {
                    listFalse = scopeStack.pop();
                }
            ) ?
            {   
                _exprContent = _exprDecisionStack.pop();
                listTrue = scopeStack.pop();
                CommandDecision cmd = new CommandDecision(_exprContent, listTrue, listFalse);
                scopeStack.peek().add(cmd);
                listTrue = null;
                listFalse = null;
            }
        ;


cmdEscolha   :  'escolha' {
                    _exprID = null;
                    _exprContent = "";
                }
                '('
                Id {
                    _exprID = _input.LT(-1).getText();
                    checkID(_exprID);

                    if(!hasType(_exprID, Variable.INT) && !hasType(_exprID, Variable.TEXT)){
                        throw new SemanticException("Variable " + _exprID + " must be of type int or text");
                    }
                }
                ')' 
                '{' {
                    _exprDecisionStack.push(_exprID);
                    _switchCaseStack.push(new ArrayList<CommandSwitchCase>());
                    listTrue = null;
                    listFalse = null;
                }
                caso+
                '}'
                {
                    _exprContent = _exprDecisionStack.pop();
                    ArrayList<CommandSwitchCase> cases = _switchCaseStack.pop();
                    CommandSwitch cmd = new CommandSwitch(_exprContent, cases);
                    scopeStack.peek().add(cmd);
                }
            ;


caso    :   'caso' 
            '('
            (
                Texto   {
                    String switchVariable = _exprDecisionStack.peek();
                    _exprContent = _input.LT(-1).getText();

                    if(hasType(switchVariable, Variable.INT)){
                        throw new SemanticException("Expected a number, found a text: " + _exprContent);
                    }
                }
                |
                Num     {
                    String switchVariable = _exprDecisionStack.peek();
                    _exprContent = _input.LT(-1).getText();

                    if(hasType(switchVariable, Variable.TEXT)){
                        throw new SemanticException("Expected a text, found a number: " + _exprContent);
                    }
                }
            )
            ')'
            '{' {
                _exprDecisionStack.push(_exprContent);
                scopeStack.push(new ArrayList<AbstractCommand>()); 
                listTrue = null;
            }
            cmd+
            '}'
            {
                _exprContent = _exprDecisionStack.pop();
                listTrue = scopeStack.pop();
                CommandSwitchCase cmd = new CommandSwitchCase(_exprContent, listTrue);
                _switchCaseStack.peek().add(cmd);
            }
        ;



cmdRepeticao    :   'enquanto'  {
                        _exprID = null;
                        _exprContent = "";
                    }
                    '('
                    expr
                    ')' {
                        _exprDecisionStack.push(_exprContent);
                        scopeStack.push(new ArrayList<AbstractCommand>()); 
                        listTrue = null;
                    }
                    '{' cmd+ '}'
                    {
                        _exprContent = _exprDecisionStack.pop();
                        listTrue = scopeStack.pop();
                        CommandLoop cmd = new CommandLoop(_exprContent, listTrue);
                        scopeStack.peek().add(cmd);
                        listTrue = null;
                    }
                ;


cmdExpr :   Id  {
                _exprID = _input.LT(-1).getText();
                checkID(_exprID);
            }
            ':=' {  _exprContent = "";  }
            (
                    expr    {
                        if(hasType(_exprID, Variable.INT)){
                            _exprContent = String.format("(int)(%s)", _exprContent);
                        }
                        
                        if(hasType(_exprID, Variable.DOUBLE)){
                            _exprContent = String.format("(double)(%s)", _exprContent);
                        }

                        CommandAssignment cmd = new CommandAssignment(_exprID, _exprContent);
                        scopeStack.peek().add(cmd);
                    }
                |   Texto   {
                        _exprContent += _input.LT(-1).getText();
                        CommandAssignment cmd = new CommandAssignment(_exprID, _exprContent);
                        scopeStack.peek().add(cmd);
                    }
            )
            {   _usedVariables.add(_exprID);    }
        ;


op_rel  :   '<'     {  _exprContent += _input.LT(-1).getText(); }
        |   '<='    {  _exprContent += _input.LT(-1).getText(); }
        |   '>'     {  _exprContent += _input.LT(-1).getText(); }
        |   '>='    {  _exprContent += _input.LT(-1).getText(); }
        |   '=='    {  _exprContent += _input.LT(-1).getText(); }
        |   '!='    {  _exprContent += _input.LT(-1).getText(); }
        ;


expr    :   termo ((op_mat | op_rel) termo)*
        ;


termo   :   Id {
                checkID(_input.LT(-1).getText());
                if(_exprID == null || checkTypes(_exprID, _input.LT(-1).getText())){
                    if(getLastOperator(_exprContent).equals("**")){
                        _exprContent = replacePower(_exprContent, _input.LT(-1).getText());
                    }
                    else _exprContent += _input.LT(-1).getText();
                }
                else {
                    throw new SemanticException("Type mismatch: " + _exprID + " and " + _input.LT(-1).getText());
                }
            }
        |   Num {
                if(_exprID == null || hasType(_exprID, Variable.INT) || hasType(_exprID, Variable.DOUBLE)){
                    if(getLastOperator(_exprContent).equals("**")){
                        _exprContent = replacePower(_exprContent, _input.LT(-1).getText());
                    }
                    else _exprContent += _input.LT(-1).getText();
                }
                else {
                    throw new SemanticException("Type mismatch: " + _exprID + " and " + _input.LT(-1).getText());
                }
            }
        ;

Id      :   ([a-z] | [A-Z]) ([a-z] | [A-Z] | [0-9])*
        ;


tipo    :   'Inteiro' {   _variableType = Variable.INT;     } 
        |   'Decimal' {   _variableType = Variable.DOUBLE;  }
        |   'Texto'   {   _variableType = Variable.TEXT;    }
        ;


Num :   Decimal
    |   Inteiro
    ;

Decimal : [0-9]+ ('.' [0-9]+)?
        ;

Inteiro : [0-9]+
        ;


Texto   :   '"' ([a-z] | [A-Z] | [0-9] | ' ')+ '"'
        ;


op_mat  :   '+'   {  _exprContent += _input.LT(-1).getText(); }
        |   '-'   {  _exprContent += _input.LT(-1).getText(); }
        |   '*'   {  _exprContent += _input.LT(-1).getText(); }
        |   '/'   {  _exprContent += _input.LT(-1).getText(); }
        |   '**'  {  _exprContent += _input.LT(-1).getText(); }
        ;


Espaco  :   (' ' | '\t' | '\n' | '\r') -> skip;