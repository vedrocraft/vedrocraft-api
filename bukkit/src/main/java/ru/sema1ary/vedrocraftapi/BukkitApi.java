package ru.sema1ary.vedrocraftapi;

import org.bukkit.plugin.java.JavaPlugin;
import ru.sema1ary.vedrocraftapi.item.listener.ItemListener;

public final class BukkitApi extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ItemListener(), this);
    }

    @Override
    public void onDisable() {}
}
