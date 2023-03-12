package ru.job4j.bank.controller;

import org.springframework.web.bind.annotation.*;
import ru.job4j.bank.model.User;
import ru.job4j.bank.service.BankService;

import java.util.Map;

/**
 * REST контроллер для пользователя
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final BankService bankService;

    public UserController(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Сохранить пользователя в хранилище
     * @param body - тело запроса с полями username, password.
     * @return тип {@link ru.job4j.bank.model.User} новый пользователь
     */
    @PostMapping
    public User save(@RequestBody Map<String, String> body) {
        var user = new User(body.get("username"), body.get("password"));
        bankService.addUser(user);
        return user;
    }

    /**
     * Найти пользователя в хранилище по паролю
     * @param password тип {@link java.lang.String} строка с паролем
     * @return  тип {@link ru.job4j.bank.model.User} найденный пользователь
     *  = NULL, если не найден.
     */
    @GetMapping
    public User findByPassport(@RequestParam String password) {
        return bankService.findByPassport(password).orElse(null);
    }
}