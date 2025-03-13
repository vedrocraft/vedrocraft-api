package ru.sema1ary.vedrocraftapi.service;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

@UtilityClass
@SuppressWarnings("unused")
public class PluginServiceGetter implements ServiceGetter {
    public <T> T getServiceFromPlugin(@NonNull String pluginName, @NonNull Class<T> serviceClass) {
       Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
       assert plugin != null;
       return ((ServiceGetter) plugin).getService(serviceClass);
    }
}
