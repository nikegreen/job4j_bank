package ru.job4j.bank.service;

import ru.job4j.bank.model.Account;
import ru.job4j.bank.model.User;

import java.util.Optional;

/**
 * интерфейс банка
 */
public interface BankService {

    /**
     * Добавить пользователя
     * @param user тип {@link ru.job4j.bank.model.User} новый пользователь
     */
    void addUser(User user);

    /**
     * Добавить счёт пользователя
     * @param passport тип {@link java.lang.String} строка с паспортом
     * @param account тип {@link ru.job4j.bank.model.Account} счёт
     */
    void addAccount(String passport, Account account);

    /**
     * Поиск пользователя по паспорту
     * @param passport тип {@link java.lang.String} строка с паспортом
     * @return тип {@link java.util.Optional<ru.job4j.bank.model.User>} пользователь
     */
    Optional<User> findByPassport(String passport);

    /**
     * Поиск счёта по реквизитам и паспорту
     * @param passport тип {@link java.lang.String} строка с паспортом
     * @param requisite тип {@link java.lang.String} строка с реквизитами
     * @return тип {@link java.util.Optional<ru.job4j.bank.model.Account>} счёт
     */
    Optional<Account> findByRequisite(String passport, String requisite);

    /**
     * Перевод денег
     * со счёта
     * @param srcPassport тип {@link java.lang.String} строка с паспортом
     * @param srcRequisite тип {@link java.lang.String} строка с реквизитами
     * на счёт
     * @param destPassport тип {@link java.lang.String} строка с паспортом
     * @param destRequisite тип {@link java.lang.String} строка с реквизитами
     * @param amount - переводимая сумма
     * @return тип boolean. Результат перевода:
     * true  - успешно переведено
     * false - не переведено
     */
    boolean transferMoney(String srcPassport, String srcRequisite,
                          String destPassport, String destRequisite, double amount);

}
