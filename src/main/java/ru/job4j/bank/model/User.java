package ru.job4j.bank.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class User extends Id {

    private String passport;

    private String username;

    private List<Account> accounts = new CopyOnWriteArrayList<>();

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    // getters/setters, equals() & hashCode()

}
