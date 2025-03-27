package ru.sema1ary.vedrocraftapi.item.listener;

import lombok.NonNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.sema1ary.vedrocraftapi.item.util.ActionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryClickListener implements Listener {
    private static final Map<String, Consumer<InventoryClickEvent>> actionMap = new HashMap<>();

    public static void registerAction(@NonNull String uuid, @NonNull Consumer<InventoryClickEvent> action) {
        actionMap.put(uuid, action);
    }

    @EventHandler
    public void onDrop(InventoryClickEvent event) {
        ActionUtil.acceptEvent(actionMap, event, event.getCurrentItem());
    }
}