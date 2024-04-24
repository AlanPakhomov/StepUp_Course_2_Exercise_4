package ru.сourses.task3.cash;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CashingHandler implements InvocationHandler {
    final static Long EXPIRE_CASH_TIME = 10_000l;
    final static int MAX_CASH_SIZE = 100;
    private final Object objIncome;
    private Date timeToClear = new Date(System.currentTimeMillis() + EXPIRE_CASH_TIME);
    private Map<Integer, Object[]> cashResults = new HashMap<>();

    public CashingHandler(Object objIncome) {
        this.objIncome = objIncome;
    }

    private void clearCash() throws InterruptedException {
        CashCleaner cleaner = new CashCleaner(cashResults);
        Thread t = new Thread(cleaner);
        t.start();
        Thread.sleep(500);//задержка, позволяющая потоку очистить кэш, перед наполенением
    }

    //расчет ключа с учетом всех полей объекта
    //искл. счетчик - т.к это искусственное поле для тестов
    private int getKey() throws IllegalAccessException {
        int key = objIncome.hashCode();
        Field[] fields = objIncome.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].getName() == "counter") continue;
            Object fieldValue = fields[i].get(objIncome);
            key += fieldValue.hashCode() * (10 + i + 1);
        }
        return key;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object[] objArr = new Object[2];
        Method curMethod = objIncome.getClass().getMethod(method.getName(), method.getParameterTypes());
        //если метод помечен аннотаций @Cash - помещаем объект в Map
        //ключ - хэш объекта+хэш полей* (10 + номер поля)
        //[0] - дата+время жизни объекта(1000мс)
        //[1] - Method
        if (curMethod.isAnnotationPresent(Cash.class)) {
            int objKey = getKey();
            int cashValue = curMethod.getAnnotation(Cash.class).value();
            if (cashResults.containsKey(objKey)) {
                objArr = cashResults.get(objKey);
                if (new Date().before((Date) objArr[0])) {
                    objArr[0] = new Date(System.currentTimeMillis() + cashValue);//обновление времени жизни объекта
                    cashResults.put(objKey, objArr);
                    return objArr[1];
                }
            }
            objArr[0] = new Date(System.currentTimeMillis() + cashValue);
            objArr[1] = method.invoke(objIncome, args);
            cashResults.put(objKey, objArr);
            return objArr[1];
        }
        //чистим кэш в случае, если количество кешированных объектов превышает 100
        //либо по прошествии EXPIRE_CASH_TIME - 10сек.
        if ((new Date().after((timeToClear)) && cashResults.size() != 0) || cashResults.size() >= MAX_CASH_SIZE) {
            clearCash();
            timeToClear = new Date(System.currentTimeMillis() + EXPIRE_CASH_TIME);//обновляем время жизни кэша
        }
        return method.invoke(objIncome, args);
    }
}
