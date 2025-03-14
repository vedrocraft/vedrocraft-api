package ru.sema1ary.vedrocraftapi.service;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface UserService <T> extends Service {
    T save(@NonNull T user);

    void saveAll(@NonNull List<T> users);

    Optional<T> findById(Long id);

    Optional<T> findByUsername(@NonNull String username);

    List<T> findAll();

    T getUser(@NonNull String username);
}
