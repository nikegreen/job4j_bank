package ru.job4j.bank.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Account extends Id {

    private String requisite;

    private double balance;

    private User user;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    // getters/setters, equals() & hashCode()

}