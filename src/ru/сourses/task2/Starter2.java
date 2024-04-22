package ru.сourses.task2;

import ru.сourses.task2.math.Fraction;
import ru.сourses.task2.math.Fractionable;
import ru.сourses.task2.cach.Utils;

public class Starter2 {
    public static void main(String... args) {
        Fraction fr= new Fraction(2,3);
        Fractionable num =  Utils.cache(fr);
        num.doubleValue();
        num.doubleValue();
        num.setNum(5);
        num.doubleValue();
        System.out.println(fr.getCounterCalls());


    }


}
