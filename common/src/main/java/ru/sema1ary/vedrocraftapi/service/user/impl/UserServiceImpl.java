package ru.sema1ary.vedrocraftapi.service.user.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.sema1ary.vedrocraftapi.ormlite.dao.UserDao;
import ru.sema1ary.vedrocraftapi.ormlite.model.User;
import ru.sema1ary.vedrocraftapi.ormlite.model.gender.Gender;
import ru.sema1ary.vedrocraftapi.service.user.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao<User, Long> userDao;

    @Override
    public User save(@NonNull User user) {
        try {
            return userDao.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(@NonNull List<User> users) {
        try {
            userDao.saveAll(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return userDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(@NonNull String username) {
        try {
            return userDao.findByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(@NonNull String username) {
        return findByUsername(username).orElseGet(() -> save(User.builder()
                .username(username)
                .reputation(5000)
                .gender(Gender.OTHER)
                .isVanishActive(false)
                .isSticksEnabled(true)
                .build()));
    }
}
