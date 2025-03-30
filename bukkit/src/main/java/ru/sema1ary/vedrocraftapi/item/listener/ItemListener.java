package ru.sema1ary.vedrocraftapi.item.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import ru.sema1ary.vedrocraftapi.item.util.ItemActionUtil;

public class ItemListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemActionUtil.acceptAction(event, event.getItem());
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        ItemActionUtil.acceptAction(event, event.getPlayer().getActiveItem());
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        ItemActionUtil.acceptAction(event, event.getItem().getItemStack());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemActionUtil.acceptAction(event, event.getItemDrop().getItemStack());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemActionUtil.acceptAction(event, event.getCurrentItem());
    }

    @EventHandler
    public void onHeld(PlayerItemHeldEvent event) {
        ItemActionUtil.acceptAction(event, event.getPlayer().getActiveItem());
    }
}
