package ru.сourses.task2.cach;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CachingHandler implements InvocationHandler {
    private final Object objIncome;
    private final Map<Object, Object[]> casheArgsMap = new HashMap<>();
    private final Map<Object, Object> casheResultsMap = new HashMap<>();

    public CachingHandler(Object objIncome) {
        this.objIncome = objIncome;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object[] casheArgs;
        Boolean bNeedCash = false;
        Object res = new Object();
        for (Method mtd : objIncome.getClass().getDeclaredMethods()) {
            if (mtd.getName().equals(method.getName()) && mtd.isAnnotationPresent(Cach.class)) {
                mtd.setAccessible(true);
                casheArgs = casheArgsMap.get(objIncome);

                if (casheResultsMap.containsKey(objIncome) ){
                    if (casheArgs != null && args != null) {
                        if (casheArgs.length == args.length) {
                            for (int i = 0; i < casheArgs.length; i++) {
                                if (!casheArgs[i].equals(args[i])) {
                                    bNeedCash = true;
                                }
                            }
                        } else bNeedCash = true;
                    }
                }else bNeedCash = true;

                if (bNeedCash) {
                    res = method.invoke(objIncome, args);
                    casheResultsMap.put(objIncome, res);
                    casheArgsMap.put(objIncome, args);
                } else if (casheResultsMap.containsKey(objIncome) ){
                    res = casheResultsMap.get(objIncome);
                } else
                    throw new RuntimeException("Error cashing object!");

                break;
            } else if (mtd.getName().equals(method.getName()) && mtd.isAnnotationPresent(Mutator.class)) {
                if (casheResultsMap.containsKey(objIncome)) casheResultsMap.remove(objIncome);
                if (casheArgsMap.containsKey(objIncome)) casheArgsMap.remove(objIncome);
                res = method.invoke(objIncome, args);
                break;
            }
        }

        return res;
    }
}
