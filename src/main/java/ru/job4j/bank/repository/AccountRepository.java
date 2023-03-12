package ru.job4j.bank.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.bank.model.Account;

import java.util.Optional;

/**
 * Хранилище счетов
 */
@Repository
public class AccountRepository extends Store<Account> {

    /**
     * Найти счёт по реквизитам и паспорту
     * @param passport - паспорт {@link java.lang.String}
     * @param requisite - реквизиты {@link java.lang.String}
     * @return тип {@link java.util.Optional<Account>} счет пользователя.
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        return store.values().stream()
                .filter(a -> a.getUser().getPassport().equals(passport)
                        && a.getRequisite().equals(requisite))
                .findFirst();
    }

}

