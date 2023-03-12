package ru.job4j.bank.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Модель пользователя
 */
@Data
public class User extends Id {
    /**
     * Пароль пользователя
     */
    private String passport;

    /**
     * Имя пользователя
     */
    private String username;

    /**
     * Счета пользователя
     */
    private List<Account> accounts = new CopyOnWriteArrayList<>();

    /**
     * Конструктор пользователя
     * @param passport - паспорт
     * @param username - имя пользователя
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    // getters/setters, equals() & hashCode()
}
