package ru.sema1ary.vedrocraftapi;

import org.bukkit.plugin.java.JavaPlugin;
import ru.sema1ary.vedrocraftapi.item.listener.DropListener;
import ru.sema1ary.vedrocraftapi.item.listener.InteractListener;
import ru.sema1ary.vedrocraftapi.item.listener.InventoryClickListener;
import ru.sema1ary.vedrocraftapi.item.listener.PickupListener;

public final class BukkitApi extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PickupListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
    }

    @Override
    public void onDisable() {}
}
