package ru.sema1ary.vedrocraftapi.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import ru.sema1ary.vedrocraftapi.service.ConfigService;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private final Plugin plugin;
    private final HashMap<String, Object> configurationMap = new HashMap<>();

    @Override
    public void enable() {
        plugin.saveDefaultConfig();

        if(!isSettingExists("reload-message")) {
            set("reload-message", "<green>Плагин перезапущен.");
        }

        reload();
    }

    @Override
    public void reload() {
        plugin.reloadConfig();

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("configuration");

        if(section == null) {
            log.error("The config does not contain a configuration section.");
            return;
        }

        section.getKeys(false).forEach(string -> configurationMap.put(string, plugin.getConfig()
                .get("configuration." + string)));
    }

    @Override
    @SuppressWarnings("all")
    public <T> @NonNull T get(@NonNull String index) {
        T object = (T) configurationMap.get(index);

        if(object == null) {
            log.error("[ConfigService] {} dont exists!", index);
        }

        return object;
    }

    @Override
    public <T> void set(@NonNull String index, T value) {
        acceptSet(index, value);

        plugin.getConfig().set("configuration." + index, value);
        plugin.saveConfig();
    }

    private <T> void acceptSet(@NonNull String index, T value) {
        boolean isContains = configurationMap.containsKey(index);
        if(value == null && isContains) {
            configurationMap.remove(index);
            return;
        }

        if(isContains) {
            configurationMap.replace(index, value);
        } else {
            configurationMap.put(index, value);
        }
    }

    @Override
    public boolean isSettingExists(@NonNull String index) {
        return configurationMap.containsKey(index);
    }
}
