package ru.сourses.task3.math;

import ru.сourses.task3.cash.Cash;
import ru.сourses.task3.cash.Mutator;

public class Fraction implements Fractionable {
    private int num;
    private int denum;
    private int counter;

    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    @Mutator
    public void setNum(int num) { this.num = num; }

    public void setCounter(int counter) { this.counter = counter; }

    @Mutator
    public void setDenum(int denum) { this.denum = denum; }

    @Override
    @Cash(1000)
    public double doubleValue() {
        counter += 1;
        return (double) num / denum;
    }
}
