package ru.sema1ary.vedrocraftapi.item.listener;

import lombok.NonNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import ru.sema1ary.vedrocraftapi.item.util.ActionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PickupListener implements Listener {
    private static final Map<String, Consumer<EntityPickupItemEvent>> actionMap = new HashMap<>();

    public static void registerAction(@NonNull String uuid, @NonNull Consumer<EntityPickupItemEvent> action) {
        actionMap.put(uuid, action);
    }

    @EventHandler
    public void onDrop(EntityPickupItemEvent event) {
        ActionUtil.acceptEvent(actionMap, event, event.getItem().getItemStack());
    }
}
