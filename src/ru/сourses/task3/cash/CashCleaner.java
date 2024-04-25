package ru.сourses.task3.cash;

public class CashCleaner implements Runnable {
    private CashingHandler cashHand;

    public CashCleaner(CashingHandler cashHand) {
        this.cashHand = cashHand;
    }

    public void run() {
        try {
            cashHand.cashCleaner();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}