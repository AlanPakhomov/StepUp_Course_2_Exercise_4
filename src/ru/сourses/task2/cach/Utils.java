package ru.сourses.task2.cach;
import java.lang.reflect.Proxy;

public class Utils {
    public static <T> T cache(T objIncome) {
        return (T) Proxy.newProxyInstance(
                objIncome.getClass().getClassLoader(),
                objIncome.getClass().getInterfaces(),
                new CachingHandler(objIncome)
        );
    }

}