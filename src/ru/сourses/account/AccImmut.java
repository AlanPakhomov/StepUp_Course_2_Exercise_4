package ru.сourses.account;

import java.util.HashMap;
import java.util.Map;

final public class AccImmut {
    private String name;
    private Map<Currency, Integer> currAmnt;

    public String getName() {
        return name;
    }

    public Map<Currency, Integer> getCurrAmnt() {
        Map<Currency, Integer> crmnt = new HashMap<>();

        for ( Map.Entry<Currency, Integer> crm : currAmnt.entrySet()) {
            crmnt.put(crm.getKey(), crm.getValue());
        }
        return crmnt;
    }

    public AccImmut(String name, Map<Currency, Integer> currAmnt) {
        this.name = name;
        this.currAmnt = currAmnt;
    }

    @Override
    public  String toString() {
        return "Immutable obj.  name=" + name + "  wallet " + currAmnt.toString();
    }
}
