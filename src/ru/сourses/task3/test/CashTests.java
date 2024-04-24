package ru.сourses.task3.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.сourses.task3.cash.Utils;
import ru.сourses.task3.math.Fraction;
import ru.сourses.task3.math.Fractionable;

public class CashTests {
    @Test
    @DisplayName("1.1 Check normal cash")
    public void cashObject() {
        Fraction fr = new Fraction(4, 2);
        Fractionable interFr = Utils.cash(fr);

        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        Assertions.assertEquals( interFr.doubleValue(), 2, "Result of doubleValue is wrong");
        Assertions.assertEquals( fr.getCounter(), 1,"Counter of cashings has wrong value");
    }

    @Test
    @DisplayName("1.2 Check cash value - 1000ms")
    public void cashCashValue() throws InterruptedException {
        Fraction fr = new Fraction(6, 2);
        Fractionable interFr = Utils.cash(fr);

        interFr.doubleValue();
        Thread.sleep(1001);
        interFr.doubleValue();
        Thread.sleep(1001);
        interFr.doubleValue();
        Thread.sleep(1001);
        interFr.doubleValue();

        Assertions.assertEquals( interFr.doubleValue(), 3, "Result of doubleValue is wrong");
        Assertions.assertEquals( fr.getCounter(), 4,"Counter of cashings has wrong value");
    }

    @Test
    @DisplayName("1.3 Check setNum/Denum - add to cash")
    public void cashsSetnumDenum(){
        Fraction fr = new Fraction(6, 2);
        Fractionable interFr = Utils.cash(fr);

        interFr.doubleValue();
        interFr.setNum(9);
        interFr.doubleValue();
        interFr.setDenum(6);
        interFr.doubleValue();
        interFr.setDenum(3);
        interFr.doubleValue();

        Assertions.assertEquals( interFr.doubleValue(), 3, "Result of doubleValue is wrong");
        Assertions.assertEquals( fr.getCounter(), 4,"Counter of cashings has wrong value");
    }

    @Test
    @DisplayName("1.4 Check setNum/Denum - dont add to cash")
    public void cashsSetnumDenum2(){
        Fraction fr = new Fraction(6, 2);
        Fractionable interFr = Utils.cash(fr);

        interFr.doubleValue();
        interFr.setNum(8);
        interFr.doubleValue();
        interFr.setNum(7);
        interFr.doubleValue();
        interFr.setNum(8);
        interFr.doubleValue();
        interFr.setNum(6);
        interFr.doubleValue();
        interFr.setDenum(4);
        interFr.doubleValue();
        interFr.setDenum(3);
        interFr.doubleValue();
        interFr.setDenum(4);
        interFr.doubleValue();
        interFr.setDenum(2);
        interFr.doubleValue();

        Assertions.assertEquals( interFr.doubleValue(), 3, "Result of doubleValue is wrong");
        Assertions.assertEquals( fr.getCounter(), 5,"Counter of cashings has wrong value");
    }

    @Test
    @DisplayName("1.5 Check cleaning cash by MAX size - 100")
    public void cashsCleanMaxSize() throws InterruptedException {
        Fraction fr = new Fraction(1, 2);
        Fractionable interFr = Utils.cash(fr);

        for (int i = 0; i < 100; i++) {
            interFr.doubleValue();
            interFr.setNum(i);
        }
        Thread.sleep(1001);
        interFr.setCounter(0);
        interFr.doubleValue();
        interFr.setNum(6);

        Assertions.assertEquals( fr.getCounter(), 1,"Counter of cashings has wrong value");
        Assertions.assertEquals( interFr.doubleValue(), 3, "Result of doubleValue is wrong");
    }

    @Test
    @DisplayName("1.6 Check cleaning cash by MAX time - 10000mc")
    public void cashCleanExpTime() throws InterruptedException {
        Fraction fr = new Fraction(1, 2);
        Fractionable interFr = Utils.cash(fr);

        for (int i = 0; i < 51; i++) {
            interFr.doubleValue();
            interFr.setNum(i);
        }
        Thread.sleep(10001);
        interFr.setCounter(0);
        for (int i = 20; i < 71; i++) {
            interFr.doubleValue();
            interFr.setNum(i);
        }
        Assertions.assertEquals( fr.getCounter(), 50,"Counter of cashings has wrong value");
        Assertions.assertEquals( interFr.doubleValue(), 35, "Result of doubleValue is wrong");
    }

    @Test
    @DisplayName("1.7 Check using non cash function")
    public void cashUsingSimpleFun() throws InterruptedException {
        Fraction fr = new Fraction(1, 2);
        Fractionable interFr = Utils.cash(fr);

        interFr.setCounter(1);
        interFr.setCounter(2);
        interFr.setCounter(3);

        Assertions.assertEquals( fr.getCounter(), 3,"Counter of cashings has wrong value");
    }
}
