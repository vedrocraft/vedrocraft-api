package ru.sema1ary.vedrocraftapi.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.sema1ary.vedrocraftapi.item.listener.DropListener;
import ru.sema1ary.vedrocraftapi.item.listener.InteractListener;
import ru.sema1ary.vedrocraftapi.item.listener.InventoryClickListener;
import ru.sema1ary.vedrocraftapi.item.listener.PickupListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class ItemBuilder {
    private final ItemMeta itemMeta;
    private final ItemStack itemStack;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    private Consumer<EntityPickupItemEvent> pickupItemEvent;
    private Consumer<PlayerDropItemEvent> dropItemEvent;
    private Consumer<InventoryClickEvent> inventoryClickEvent;
    private Consumer<PlayerInteractEvent> interactEvent;

    public ItemBuilder displayName(@NonNull String name) {
        itemMeta.displayName(miniMessage.deserialize(name));
        return this;
    }

    public ItemBuilder lore(@NonNull List<String> list) {
        List<Component> componentList = new ArrayList<>();
        list.forEach(string -> componentList.add(miniMessage.deserialize(string)));
        itemMeta.lore(componentList);
        return this;
    }

    public ItemBuilder unbreakable(boolean value) {
        itemMeta.setUnbreakable(value);
        return this;
    }

    public ItemBuilder headOwner(@NonNull String value, @NonNull String signature) {
        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID(), "vedrocraft");
        profile.setProperty(new ProfileProperty("textures", value, signature));
        ((SkullMeta) itemMeta).setPlayerProfile(profile);
        return this;
    }

    public ItemBuilder pickAction(@NonNull Consumer<EntityPickupItemEvent> action) {
        pickupItemEvent = action;
        return this;
    }

    public ItemBuilder dropAction(@NonNull Consumer<PlayerDropItemEvent> action) {
        dropItemEvent = action;
        return this;
    }

    public ItemBuilder inventoryClickAction(@NonNull Consumer<InventoryClickEvent> action) {
        inventoryClickEvent = action;
        return this;
    }

    public ItemBuilder interactAction(@NonNull Consumer<PlayerInteractEvent> action) {
        interactEvent = action;
        return this;
    }

    protected ItemBuilder(@NonNull Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public static ItemBuilder builder(@NonNull Material material) {
        return new ItemBuilder(material);
    }

    public ItemStack build() {
        String uuid = String.valueOf(UUID.randomUUID());
        itemMeta.getPersistentDataContainer().set(NamespacedKey.minecraft("vedrocraft_action_uuid"),
                PersistentDataType.STRING, uuid);

        itemStack.setItemMeta(itemMeta);
        registerActions(uuid);
        return itemStack;
    }

    private void registerActions(@NonNull String uuid) {
        if(pickupItemEvent != null) {
            PickupListener.registerAction(uuid, pickupItemEvent);
        }
        if(dropItemEvent != null) {
            DropListener.registerAction(uuid, dropItemEvent);
        }
        if(inventoryClickEvent != null) {
            InventoryClickListener.registerAction(uuid, inventoryClickEvent);
        }
        if(interactEvent != null) {
            InteractListener.registerAction(uuid, interactEvent);
        }
    }
}
