package ru.sema1ary.vedrocraftapi.ormlite.dao;

import com.j256.ormlite.dao.Dao;
import lombok.NonNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface UserDao <T, ID> extends Dao<T, ID> {
    T save(@NonNull T user) throws SQLException;

    void saveAll(@NonNull List<T> users) throws SQLException;

    Optional<T> findById(Long id) throws SQLException;

    Optional<T> findByUsername(@NonNull String username) throws SQLException;

    List<T> findAll() throws SQLException;
}
