package ru.job4j.bank.model;

import lombok.Data;

@Data
public abstract class Id {

    protected int id;

    public Id() {}

    // getters/setters, equals() & hashCode()
}