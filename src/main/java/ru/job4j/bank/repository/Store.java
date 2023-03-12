package ru.job4j.bank.repository;

import ru.job4j.bank.model.Id;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Абстрактное хранилище
 * @param <T> тип наследник класса Id
 */
public abstract class Store<T extends Id> {

    protected Map<Integer, T> store = new ConcurrentHashMap<>();

    protected AtomicInteger idGenerator = new AtomicInteger(0);

    /**
     * Сохранить или обновить в хранилище
     * @param model модель
     * @return обновлённая или сохранённая модель
     */
    public T saveOrUpdate(T model) {
        if (store.containsKey(model.getId())) {
            store.put(model.getId(), model);
            return model;
        }
        var id = idGenerator.incrementAndGet();
        model.setId(id);
        return store.put(id, model);
    }

    /**
     * Удалить модель из хранилища
     * @param id - идентификатор удаляемой модели
     * @return - удалённая модель из хранилища
     */
    public Optional<T> delete(int id) {
        return Optional.ofNullable(store.remove(id));
    }

    /**
     * Список всех моделей из хранилища
     * @return список моделей
     */
    public List<T> findAll() {
        return new CopyOnWriteArrayList<>(store.values());
    }

    /**
     * Поиск модели в хранилище по идентификатору
     * @param id - идентификатор модели
     * @return найденная модель
     */
    public Optional<T> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }
}
