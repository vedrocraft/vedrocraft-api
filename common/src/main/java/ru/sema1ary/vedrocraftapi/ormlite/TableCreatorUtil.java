package ru.sema1ary.vedrocraftapi.ormlite;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
class TableCreatorUtil {
    @SneakyThrows
    void create(@NonNull JdbcPooledConnectionSource connectionSource, @NonNull Class<?>... modelClasses) {
        for(Class<?> modelClass: modelClasses) {
            TableUtils.createTableIfNotExists(connectionSource, modelClass);
        }
    }
}
