package ru.sema1ary.vedrocraftapi.item.listener;

import lombok.NonNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import ru.sema1ary.vedrocraftapi.item.util.ActionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DropListener implements Listener {
    private static final Map<String, Consumer<PlayerDropItemEvent>> actionMap = new HashMap<>();

    public static void registerAction(@NonNull String uuid, @NonNull Consumer<PlayerDropItemEvent> action) {
        actionMap.put(uuid, action);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ActionUtil.acceptEvent(actionMap, event, event.getItemDrop().getItemStack());
    }
}
