package ru.сourses.test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import ru.сourses.account.AccImmut;
import ru.сourses.account.Account;
import ru.сourses.account.Currency;

public class AccountTests {
    @Test
    @DisplayName("1.1 Empty name if account owner")
    public void nameIsEmpty() {
        String name = null;
        try {
            Account acnt = new Account("");
        } catch (IllegalArgumentException e) {
            return;
        }
        throw new AssertionFailedError("Empty name if account owner test error");
    }

    @Test
    @DisplayName("1.2 Negative currency amount")
    public void negativeCurrAmnt() {
        try {
            Account acnt = new Account("Vasya");
            acnt.addCur(Currency.USD, -123, true);
        } catch (IllegalArgumentException e) {
            return;
        }
        throw new AssertionFailedError("Negative currency amount test error");
    }

    @Test
    @DisplayName("1.3 Normal creation account")
    public void normalAccount() {
        try {
            Account acnt = new Account("Vasya");
            acnt.addCur(Currency.USD, 123, true);
            acnt.addCur(Currency.EUR, 126, true);
            acnt.setName("Petr", true);
            acnt.setName("Kolya", true);
            acnt.setName("Sveta", true);
            acnt.addCur(Currency.RUB, 000, true);
        } catch (IllegalArgumentException e) {
            throw new AssertionFailedError("Normal creation account test error");
        }
    }

    @Test
    @DisplayName("2.1 Trying to undo when nothing")
    public void nothingUndo() {
        try {
            Account acnt = new Account("Vasya");
            acnt.addCur(Currency.USD, 123, true);
            acnt.addCur(Currency.EUR, 126, true);
            acnt.setName("Petr", true);
            acnt.setName("Kolya", true);
            acnt.setName("Sveta", true);
            acnt.addCur(Currency.RUB, 000, true);
            for (int i = 0; i < 7; i++) {
                acnt.undo();
            }
        } catch (RuntimeException e) {
            return;
        }
        throw new AssertionFailedError("Trying to undo when nothing test error");
    }

    @Test
    @DisplayName("2.2 Normal undo changes")
    public void normalUndo() {
        Account acnt = new Account("Vasya");
        try {
            acnt.addCur(Currency.USD, 123, true);
            acnt.addCur(Currency.EUR, 126, true);
            acnt.setName("Petr", true);
            acnt.setName("Kolya", true);
            acnt.setName("Sveta", true);
            acnt.addCur(Currency.RUB, 000, true);
            for (int i = 0; i < 5; i++) {
                acnt.undo();
            }
        } catch (RuntimeException e) {
            throw new AssertionFailedError("Normal undo changes test error");
        }
        Assertions.assertEquals(acnt.toString(),"name=Vasya  wallet {USD=123}");
    }

    @Test
    @DisplayName("2.3 Full undo changes")
    public void fullUndo() {
        Account acnt = new Account("Poor Vasya");
        try {
            acnt.addCur(Currency.USD, 123, true);
            acnt.addCur(Currency.EUR, 126, true);
            acnt.setName("Petr", true);
            acnt.setName("Kolya", true);
            acnt.setName("Sveta", true);
            acnt.addCur(Currency.RUB, 000, true);
            for (int i = 0; i < 6; i++) {
                acnt.undo();
            }
        } catch (RuntimeException e) {
            throw new AssertionFailedError("Full undo changes test error");
        }
        Assertions.assertEquals(acnt.toString(),"name=Poor Vasya  wallet {}");
    }

    @Test
    @DisplayName("3.1 Get immutable account")
    public void saveAccount() {
        Account acnt = new Account("Vasya");
        AccImmut acntImmut;
        try {
            acnt.addCur(Currency.USD, 123, true);
            acnt.addCur(Currency.EUR, 126, true);
            acnt.setName("Petr", true);
            acnt.setName("Kolya", true);
            acnt.setName("Sveta", true);
            acnt.addCur(Currency.RUB, 000, true);
            acntImmut = new AccImmut(acnt);
        } catch (RuntimeException e) {
            throw new AssertionFailedError("Get immutable account test error");
        }
        Assertions.assertEquals(acntImmut.toString(),acnt.toString());
    }

    @Test
    @DisplayName("3.2 Change account after saving")
    public void changeOriginalAccount() {
        Account acnt = new Account("Vasya");
        AccImmut acntImmut;
        try {
            acnt.addCur(Currency.USD, 123, true);
            acnt.addCur(Currency.EUR, 126, true);
            acnt.setName("Petr", true);
            acnt.setName("Kolya", true);
            acnt.setName("Sveta", true);
            acntImmut = new AccImmut(acnt);
            acnt.addCur(Currency.RUB, 000, true);
        } catch (RuntimeException e) {
            throw new AssertionFailedError("Change account after saving test error");
        }
        Assertions.assertEquals(acntImmut.toString(),acnt.toString());
    }
}
