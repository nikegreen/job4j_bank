package ru.job4j.bank.controller;

import org.springframework.web.bind.annotation.*;
import ru.job4j.bank.model.Account;
import ru.job4j.bank.service.BankService;

import java.util.Map;

/**
 * REST контроллер для счёта пользователя
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final BankService bankService;

    public AccountController(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Добавить счёт в хранилище
     * @param body тело запроса с полями passport и requisite.
     * @return тип {@link ru.job4j.bank.model.Account} новый счёт.
     */
    @PostMapping
    public Account addAccount(@RequestBody Map<String, String> body) {
        var passport = body.get("passport");
        var account = new Account(body.get("requisite"), 0);
        bankService.addAccount(passport, account);
        return account;
    }

    /**
     * Поиск счёта по реквизитам
     * @param passport тип {@link java.lang.String} строка с паспортными данными
     * @param requisite тип {@link java.lang.String} строка с реквизитами
     * @return тип {@link ru.job4j.bank.model.Account} счёт пользователя
     */
    @GetMapping
    public Account findByRequisite(@RequestParam String passport, @RequestParam String requisite) {
        return bankService.findByRequisite(passport, requisite).orElse(null);
    }
}
