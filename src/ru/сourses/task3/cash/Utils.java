package ru.сourses.task3.cash;

import java.lang.reflect.Proxy;

public class Utils {
    public static <T> T cash(T objIncome) {
        return (T) Proxy.newProxyInstance(
                objIncome.getClass().getClassLoader(),
                objIncome.getClass().getInterfaces(),
                new CashingHandler(objIncome)
        );
    }

}