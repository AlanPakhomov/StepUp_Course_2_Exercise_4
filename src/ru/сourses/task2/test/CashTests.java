package ru.сourses.task2.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.сourses.task2.cach.Utils;
import ru.сourses.task2.math.Fraction;
import ru.сourses.task2.math.Fractionable;

public class CashTests {
    @Test
    @DisplayName("1.1 Check cash")
    public void casheObject() {
        Fraction fr = new Fraction(2, 3);
        Fractionable interFr = Utils.cache(fr);

        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        Assertions.assertEquals( interFr.doubleValue(), 0.6666666666666666, "Result of doubleValue is wrong");
        Assertions.assertEquals( fr.getCounterCalls(), 1,"Counter of cashings has wrong value");
    }

    @Test
    @DisplayName("1.2 reset cash by setNum")
    public void resetCasheBySetNum() {
        Fraction fr = new Fraction(10, 30);
        Fractionable interFr = Utils.cache(fr);

        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.setNum(5);
        interFr.doubleValue();
        interFr.doubleValue();

        Assertions.assertEquals( interFr.doubleValue(), 0.16666666666666666, "Result of doubleValue is wrong");
        Assertions.assertEquals(fr.getCounterCalls(), 2,"Counter of cashings has wrong value");
    }

    @Test
    @DisplayName("1.3 reset cash by setDenum")
    public void resetCasheBySetDenum() {
        Fraction fr = new Fraction(10, 20);
        Fractionable interFr = Utils.cache(fr);

        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.setDenum(15);
        interFr.doubleValue();
        interFr.setDenum(25);
        interFr.doubleValue();

        Assertions.assertEquals( interFr.doubleValue(), 0.4, "Result of doubleValue is wrong");
        Assertions.assertEquals(fr.getCounterCalls(), 3,"Counter of cashings has wrong value");
    }

    @Test
    @DisplayName("1.4 reset cash at the end")
    public void resetCasheAtTheEnd() {
        Fraction fr = new Fraction(10, 20);
        Fractionable interFr = Utils.cache(fr);

        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        interFr.setDenum(5);
        interFr.setDenum(10);
        interFr.setDenum(15);
        interFr.setNum(25);

        Assertions.assertEquals( interFr.doubleValue(), 1.6666666666666667, "Result of doubleValue is wrong");
        Assertions.assertEquals(fr.getCounterCalls(), 2,"Counter of cashings has wrong value");
    }

    @Test
    @DisplayName("1.5 object modification doesn't affect on Cash")
    public void changeCashObject() {
        Fraction fr = new Fraction(10, 20);
        Fractionable interFr = Utils.cache(fr);

        interFr.doubleValue();
        interFr.doubleValue();
        interFr.doubleValue();
        fr.setNum(1);
        interFr.doubleValue();
        fr.setDenum(255);
        interFr.doubleValue();

        Assertions.assertEquals( interFr.doubleValue(), 0.5, "Result of doubleValue is wrong");
        Assertions.assertEquals(fr.getCounterCalls(), 1,"Counter of cashings has wrong value");
    }

}
