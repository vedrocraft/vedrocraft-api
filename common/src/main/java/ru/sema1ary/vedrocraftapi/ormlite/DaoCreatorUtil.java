package ru.sema1ary.vedrocraftapi.ormlite;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
class DaoCreatorUtil {
    @SneakyThrows
    void create(@NonNull JdbcPooledConnectionSource connectionSource, @NonNull Class<?>... modelClasses) {
        for(Class<?> modelClass: modelClasses) {
            DaoManager.createDao(connectionSource, modelClass);
        }
    }
}
