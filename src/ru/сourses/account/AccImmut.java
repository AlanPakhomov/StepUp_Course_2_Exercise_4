package ru.сourses.account;

import java.util.HashMap;
import java.util.Map;

final public class AccImmut {
    private final String name;
    private final Map<Currency, Integer> currAmnt;

    public String getName() {
        return name;
    }

    public Map<Currency, Integer> getCurrAmnt() {
        return new HashMap<>(currAmnt);
    }

    public AccImmut(Account account) {
        this.name = account.getName();
        this.currAmnt = account.getCurrAmnt();
    }

    @Override
    public  String toString() {
        return "name=" + name + "  wallet " + currAmnt.toString();
    }
}
