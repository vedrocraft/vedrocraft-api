package ru.sema1ary.vedrocraftapi.ormlite;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.bukkit.plugin.Plugin;
import ru.sema1ary.vedrocraftapi.service.ConfigService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
@SuppressWarnings("unused")
public class DatabaseUtil {
    @SneakyThrows
    public void initConnectionSource(@NonNull Plugin plugin, @NonNull ConfigService configService,
                                     @NonNull Class<?>... modelClasses) {

        if(configService.get("sql-use")) {
            ConnectionSourceUtil.connectSQL(
                    configService.get("sql-host"),
                    configService.get("sql-database"),
                    configService.get("sql-user"),
                    configService.get("sql-password"),
                    modelClasses);
            return;
        }

        initConnectionSource(plugin, modelClasses);
    }

    @SneakyThrows
    public void initConnectionSource(@NonNull Plugin plugin,
                                     @NonNull Class<?>... modelClasses) {

        Path databaseFilePath = Paths.get(plugin.getDataFolder() + "/database.sqlite");

        if(!Files.exists(databaseFilePath) && !databaseFilePath.toFile().createNewFile()) {
            return;
        }

        ConnectionSourceUtil.connectNoSQLDatabase(databaseFilePath.toString(), modelClasses);
    }
}
