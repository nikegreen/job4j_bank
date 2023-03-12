package ru.job4j.bank.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.bank.model.User;

import java.util.Optional;

/**
 * Хранилище пользователей
 */
@Repository
public class UserRepository extends Store<User> {
    /**
     * Поиск пользователя по паспорту
     * @param passport - паспортные данные
     * @return тип {@link java.util.Optional<ru.job4j.bank.model.User>}
     * найденный пользователь
     */
    public Optional<User> findByPassport(String passport) {
        return store.values().stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst();
    }
}
