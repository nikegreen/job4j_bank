package ru.job4j.bank.model;

import lombok.Data;

import java.util.Objects;

/**
 * Модель счёта пользователя
 */
@Data
public class Account extends Id {

    /**
     * Реквизиты счёта
     */
    private String requisite;

    /**
     * Баланс на счёте
     */
    private double balance;

    /**
     * Пользователь владеющий счётом
     */
    private User user;

    /**
     * Конструктор модели счёта
     * @param requisite - реквизиты
     * @param balance - баланс счёта
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    // getters/setters, equals() & hashCode()

}