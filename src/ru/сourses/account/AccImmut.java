package ru.сourses.account;

import java.util.HashMap;
import java.util.Map;

final public class AccImmut {
    private String name;
    private Map<Currency, Integer> currAmnt = new HashMap<>();

    public AccImmut(String name, Map<Currency, Integer> currAmnt) {
        this.name = name;
        this.currAmnt = currAmnt;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public  String toString() {
        return "Immutable obj.  name=" + name + "  wallet " + currAmnt.toString();
    }
}
