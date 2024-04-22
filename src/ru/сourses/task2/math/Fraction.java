package ru.сourses.task2.math;

import ru.сourses.task2.cach.Cach;
import ru.сourses.task2.cach.Mutator;

public class Fraction implements Fractionable {
    private int num;
    private int denum;
    private int counterCalls;

    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
        this.counterCalls = 0;
    }

    public int getCounterCalls() {
        return counterCalls;
    }

    @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Mutator
    public void setDenum(int denum) {
        this.denum = denum;
    }

    @Override
    @Cach
    public double doubleValue() {
        counterCalls+=1;
        return (double) num / denum;
    }
}
