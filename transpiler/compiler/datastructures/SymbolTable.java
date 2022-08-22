package compiler.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, Symbol> table;

    public SymbolTable() {
        table = new HashMap<String, Symbol>();
    }

    public void add(Symbol symbol) {
        table.put(symbol.getName(), symbol);
    }

    public Symbol get(String name) {
        return table.get(name);
    }

    public boolean contains(String name) {
        return table.containsKey(name);
    }

    public ArrayList<Symbol> getValues() {
        ArrayList<Symbol> values = new ArrayList<Symbol>();
        for (Symbol symbol : table.values()) {
            values.add(symbol);
        }
        return values;
    }

    public ArrayList<String> getKeys() {
        ArrayList<String> keys = new ArrayList<String>();
        for (String key : table.keySet()) {
            keys.add(key);
        }
        return keys;
    }
}
