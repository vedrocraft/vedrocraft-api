package ru.sema1ary.vedrocraftapi.service.impl;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.sema1ary.vedrocraftapi.service.ConfigService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private final String pluginName;
    private CommentedFileConfig config;

    @Override
    @SneakyThrows
    public void enable() {
        File configFile = new File("plugins/" + pluginName + "/config.toml");
        Path path = Paths.get(configFile.getPath());

        if(!path.getParent().toFile().exists()) {
            Files.createDirectory(path.getParent());
        }

        try(CommentedFileConfig config = CommentedFileConfig.builder(path).autosave().build()) {
            if(!configFile.exists()) {
                config.set("configuration.sql-use", false);
                config.set("configuration.sql-host", "");
                config.set("configuration.sql-db", "");
                config.set("configuration.sql-user", "");
                config.set("configuration.sql-pass", "");

                config.set("configuration.reload-message", "Плагин успешно перезапущен.");
            }

            this.config = config;
            this.config.load();
        }
    }

    @Override
    public void disable() {
        config.close();
    }

    @Override
    public void reload() {
        config.close();
        config.load();
    }

    @Override
    public <T> @NonNull T get(@NonNull String index) {
        return config.get("configuration." + index);
    }

    @Override
    public <T> void set(@NonNull String index, T value) {
        config.set("configuration." + index, value);
    }
}
