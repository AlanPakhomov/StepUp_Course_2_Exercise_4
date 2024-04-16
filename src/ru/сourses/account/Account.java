package ru.сourses.account;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private String name;
    private Map<Currency, Integer> currAmnt;
    private final Deque<Action> changes;

    public Account(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Поле name не должно быть пустым");

        this.name = name;
        currAmnt = new HashMap<>();
        changes = new ArrayDeque<>();

        changes.addFirst(x->x.setName(name,false));
    }

    public String getName() {
        return name;
    }

    public Map<Currency, Integer> getCurrAmnt() {
        return currAmnt;
    }

    public void setName(String name, boolean saveChng) {
        System.out.println("setName "+this.name+" >>"+name);
        if(saveChng) changes.addLast(x->x.setName(name,false));
        this.name = name;
    }
    public void addCur(Currency cur, int amount, boolean saveChng) {
        if (amount < 0)
            throw new IllegalArgumentException("Количество валюты не может быть отрицательным");
        System.out.println("addCur "+cur+"="+amount);
        if(saveChng) changes.addLast(x->x.addCur(cur, amount, false));
        currAmnt.put(cur, amount);
    }

    public void checkUndo(){
        if (changes.isEmpty()||changes.size()==1) throw new RuntimeException("Изменений не было - откат невозможен!");
    }

    public void undo(){
        checkUndo();
        changes.pollLast();

        currAmnt.clear();
        for (Action act:changes) {
            act.make(this);
        }

        System.out.println("undo result: " + this.toString());
        System.out.println(this.changes.size());

    }

    public AccImmut getSaving(Account acnt) {
        return new AccImmut(acnt);
    }

    @Override
    public String toString() {
        return "name=" + name + "  wallet " + currAmnt.toString();
    }

}
