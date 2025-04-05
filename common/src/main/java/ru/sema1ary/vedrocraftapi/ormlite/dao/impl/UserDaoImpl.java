package ru.sema1ary.vedrocraftapi.ormlite.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import lombok.NonNull;
import ru.sema1ary.vedrocraftapi.ormlite.dao.UserDao;
import ru.sema1ary.vedrocraftapi.ormlite.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao<User, Long> {
    public UserDaoImpl(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public User save(@NonNull User user) throws SQLException {
        createOrUpdate(user);
        return user;
    }

    @Override
    public void saveAll(@NonNull List<User> users) throws SQLException {
        callBatchTasks((Callable<Void>) () -> {
            for (User user : users) {
                createOrUpdate(user);
            }
            return null;
        });
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        User result = queryForId(id);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<User> findByUsername(@NonNull String username) throws SQLException {
        QueryBuilder<User, Long> queryBuilder = queryBuilder();
        Where<User, Long> where = queryBuilder.where();
        String columnName = "username";

        SelectArg selectArg = new SelectArg(SqlType.STRING, username.toLowerCase());
        where.raw("LOWER(" + columnName + ")" + " = LOWER(?)", selectArg);
        return Optional.of(queryBuilder.queryForFirst());
    }

    @Override
    public List<User> findAll() throws SQLException {
        return queryForAll();
    }
}
