package ru.job4j.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import ru.job4j.bank.model.User;
import ru.job4j.bank.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * REST контроллер для пользователя
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getSimpleName());

    private final BankService bankService;

    private final ObjectMapper objectMapper;

    public UserController(BankService bankService, ObjectMapper objectMapper) {
        this.bankService = bankService;
        this.objectMapper = objectMapper;
    }
    /**
     * Сохранить пользователя в хранилище
     * @param body - тело запроса с полями username, password.
     * @return тип {@link ru.job4j.bank.model.User} новый пользователь
     */
    @PostMapping
    public User save(@RequestBody Map<String, String> body) {
        var username = body.get("username");
        var password = body.get("password");
        if (username == null || password == null) {
            throw new NullPointerException("Username and password mustn't be empty");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Invalid password. Password length must be more than 5 characters.");
        }
        var user = new User(username, password);
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

    /**
     * Обработчик исключений на уровне класса (т.е. этого контроллера)
     * @param e тип {@link java.lang.Exception} исключение
     * @param request  тип {@link javax.servlet.http.HttpServletRequest} HTTP запрос
     * @param response тип {@link javax.servlet.http.HttpServletResponse} HTTP ответ
     * @throws IOException исключение ввода-вывода
     * -------------------------------------------------------------
     * <b>Важно!</b> Если Вы используете Spring Boot, то в целях безопасности он ограничивает
     * отображение сообщения ошибки. Для того чтобы оно появилось в теле ответа,
     * добавьте следующую строку в application.properties:
     * <b>server.error.include-message=always</b>
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }
}