package ru.job4j.bank.service;

import org.springframework.stereotype.Service;
import ru.job4j.bank.model.Account;
import ru.job4j.bank.model.User;
import ru.job4j.bank.repository.AccountRepository;
import ru.job4j.bank.repository.UserRepository;
import java.util.Optional;

/**
 * Сервис банка
 */
@Service
public class SimpleBankService implements BankService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    /**
     * Конструктор сервиса
     * @param userRepository хранилище пользователей
     * @param accountRepository хранилище счётов
     */
    public SimpleBankService(UserRepository userRepository,
                             AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Добавить пользователя
     * @param user тип {@link ru.job4j.bank.model.User} новый пользователь
     */
    @Override
    public void addUser(User user) {
        this.userRepository.saveOrUpdate(user);
    }

    /**
     * Добавить счёт
     * @param passport тип {@link java.lang.String} строка с паспортом
     * @param account тип {@link ru.job4j.bank.model.Account} счёт
     */
    @Override
    public void addAccount(String passport, Account account) {
        this.userRepository.findByPassport(passport)
                .ifPresent(u -> {
                    account.setUser(u);
                    accountRepository.saveOrUpdate(account);
                    u.getAccounts().add(account);
                });
    }

    /**
     * Поиск пользователя по паспорту
     * @param passport тип {@link java.lang.String} строка с паспортом
     * @return тип {@link java.util.Optional<ru.job4j.bank.model.User>} пользователь
     */
    @Override
    public Optional<User> findByPassport(String passport) {
        return this.userRepository.findByPassport(passport);
    }

    /**
     * Поиск счёта по реквизитам и паспорту
     * @param passport тип {@link java.lang.String} строка с паспортом
     * @param requisite тип {@link java.lang.String} строка с реквизитами
     * @return тип {@link java.util.Optional<ru.job4j.bank.model.Account>} счёт
     */
    @Override
    public Optional<Account> findByRequisite(String passport, String requisite) {
        return accountRepository.findByRequisite(passport, requisite);
    }

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
    @Override
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        var srcAccount = findByRequisite(srcPassport, srcRequisite);
        if (srcAccount.isEmpty()) {
            return false;
        }
        var descAccount = findByRequisite(destPassport, destRequisite);
        if (descAccount.isEmpty()) {
            return false;
        }
        if (srcAccount.get().getBalance() - amount < 0) {
            return false;
        }
        srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
        descAccount.get().setBalance(descAccount.get().getBalance() + amount);
        return false;
    }
}

