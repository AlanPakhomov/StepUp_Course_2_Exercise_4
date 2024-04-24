package ru.сourses.task3.cash;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CashCleaner implements Runnable {
    private Map<Integer, Object[]> cashRes;

    public CashCleaner(Map<Integer, Object[]> cashRes) {
        this.cashRes = cashRes;
    }

    public void run() {
        Map<Integer, Object[]> res = new HashMap<>();//буферный массив
        for (int key : cashRes.keySet()) {
            if (new Date().before((Date) cashRes.get(key)[0]))
                res.put(key, cashRes.get(key));
        }
        cashRes.clear();
        for (int key : res.keySet()) {
            cashRes.put(key, res.get(key));
        }
        System.out.println();
        res.clear();
    }
}