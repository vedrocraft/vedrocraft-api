package ru.sema1ary.vedrocraftapi.item.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@UtilityClass
public class ActionUtil {
    private final NamespacedKey key = NamespacedKey.minecraft("vedrocraft_action_uuid");

    public <T> void acceptEvent(@NonNull Map<String, Consumer<T>> actionMap, T event, ItemStack itemStack) {
        if(itemStack == null || itemStack.isEmpty() || itemStack.getType().isEmpty() || itemStack.getType().isAir()) {
            return;
        }

        if(!isHasAction(actionMap.keySet(), itemStack)) {
            return;
        }

        actionMap.get(getUUID(itemStack)).accept(event);
    }

    public boolean isHasAction(@NonNull Set<String> keySet, @NonNull ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta == null) {
            return false;
        }

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if(!container.has(key)) {
            return false;
        }

        return keySet.contains(container.get(key, PersistentDataType.STRING));
    }

    public String getUUID(@NonNull ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta == null) {
            return null;
        }

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if(!container.has(key)) {
            return null;
        }

        return container.get(key, PersistentDataType.STRING);
    }
}
