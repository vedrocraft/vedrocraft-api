package ru.sema1ary.vedrocraftapi.gui;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.sema1ary.vedrocraftapi.item.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SuppressWarnings("unused")
public abstract class PagedGui {
    private final String title;

    private final int size;

    private int currentPage = 0;

    private int currentSlot = 0;

    private final List<Inventory> inventories = new ArrayList<>();

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public abstract void createInventory();

    @SuppressWarnings("unused")
    public void open(Player player) {
        if(inventories.isEmpty()) return;

        Inventory mainPage = inventories.get(currentPage);
        Inventory lastPage = inventories.get(inventories.size() - 1);

        inventories.forEach(this::createBottomMenu);

        mainPage.setItem(mainPage.getSize() - 9, null);
        lastPage.setItem(lastPage.getSize() - 1, null);

        player.openInventory(mainPage);
    }

    @SuppressWarnings("unused")
    public void addItemToInventory(ItemStack itemStack) {
        if(inventories.isEmpty()) {
            createPage(itemStack);
            return;
        }

        if (size == 9 && currentSlot == 0) {
            currentSlot++;
            return;
        }

        Inventory page = inventories.get(inventories.size() - 1);
        if(!isMoreThanMax(page)) {
            page.setItem(currentSlot, itemStack);
            currentSlot++;
            return;
        }

        if(isMoreThanMax(page)) {
            createPage(itemStack);
        }
    }

    private boolean isMoreThanMax(Inventory page) {
        if(!(size == 9)) return currentSlot >= (page.getSize() - 9);

        return currentSlot >= (page.getSize() - 1);
    }

    private void createPage(ItemStack itemStack) {
        Inventory page = Bukkit.createInventory(null, size,
                miniMessage.deserialize(title).append(Component.text(" - страница: " + (inventories.size() + 1))));
        currentSlot = 0;
        page.setItem(currentSlot, itemStack);
        currentSlot++;
        inventories.add(page);
    }

    private void createBottomMenu(Inventory page) {

        page.setItem(size - 9, ItemBuilder.builder(Material.PLAYER_HEAD)
                .displayName("<gray>Назад")

                .headOwner("ewogICJ0aW1lc3RhbXAiIDogMTc0MzA3NjA5Njk2NCwKICAicHJvZmlsZUlkIiA6ICI5OGQxYTQyNmRlMmU0NjBkYjdjNWExMmY5MGNhODg0OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJLdWJpbm9TSyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYzAxYTQ3MTEzOGM1YTY3NDVmOTdhYTBiN2I1ODNlODYwYTM5YWJhZWU2OTMzYjVlNGFiM2VhZjVmZTMyOTBmIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=",
                        "bkUVfPhwCzVs7Z/nIwBotCf8VYyvgXBCQVBijS/r1l1nNNxnZD4MkoA5LD5GPslT/CH8n0r8g12eDg4lqkdQYPXuFZCCFnUNlH2ZsbAYEDlZLe7KaxJQwVaXpz4kl+kpiufkPUpXhJkk/HjIBR/V05zT74E0ddqFqhWBTxJt9Xgnh6o1qPRVQ0ks+R19FskN+/t/Y7hXUPrCnhFs8xKg0a7fTID2XBVPtFu1xsg3yuivrQEZJBDeZ++0P4mhtpnNsEjga5JDTl4rO6T1oBDj/BfW+KPi8kFoDtyB/Igaco7OCLIGA9wC/1gXbdolZOs2gAyTC6gU7hkJF7QhWeIsEA4MVkXg8coHD53nHPRXqEZdn+Jln73FMLmfrsaXnDAyKUVH1d76OatoTs8tml2FUCc2OMxzbEe17jet+r2Nd6lTtLTA7oJRha/cSuEQ1Ax1Oic8A/SH6qa7Vpsf8bXZEuxikY8VyBh/ErpJqcbJO4+FpzFyx2x++jMTtfzv6kGzSnr1cJuHNH5m3gU0IxbtDTHWfQJskWv1iZXwEtFfDcWHbFRmub5u3isKLuCNd+CWkrttMhuUGilGCKT0QC/Bv+AtNLLS9O3iDKYe9SUs4tu4leWm8kRvtBpUCjrqRZV7S7FAUgGAkvqJg2+UIN2QFvB8V8ALMokP1GVZpQvFmNs=")

                .inventoryClickAction(event -> {
                    if(!(event.getWhoClicked() instanceof Player player)) {
                        return;
                    }

                    changePage(player, currentPage - 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1f, 1f);
                    event.setCancelled(true);
                })
                .build());

        page.setItem(size - 1, ItemBuilder.builder(Material.PLAYER_HEAD)
                .displayName("<gray>Вперёд")
                .headOwner("ewogICJ0aW1lc3RhbXAiIDogMTc0MzA3NjI5OTQ0OSwKICAicHJvZmlsZUlkIiA6ICI0NDAzZGM1NDc1YmM0YjE1YTU0OGNmZGE2YjBlYjdkOSIsCiAgInByb2ZpbGVOYW1lIiA6ICI4Qml0czFCeXRlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzhlOGU3Zjk4NzM3NzM3NTBmNTQwNmUyOWM0ZTdiMjc3MDI0Y2Q3N2Y0YmYyZmQ1MTFiZGM0NDA3Njk2NTIyYTYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==",
                        "Y/CxO2xrZF6m2PyiogF/y2xVn673d/fB4D5kHEZaBNoKeo2l4CJhGEifquVj4/63cG9TFxO/WnnrtGYQ63vtNVubw39XxwViLZayx2sORWIsNkh98aOWVy2XFsCXQa5fuvYUiefIFA9/LZSx/zbyHV0cdBwBR9ni+Mb8t3rEp6nOPmRMLuBWoZI3pNQHkAM5Exs+PjwLoxfj3lP43TwrVhUFjapmw68J0GIqLKrf4xkJcrE4EyVthnECqKfTaIhbIPOdPKWo1hVMoVvCXUX+hg7fitPImVrT79h0D3/Ztqf3wa3Nn8xxXDEAHQ6+iOSLw1RxbCLhtkxvKGQUGBqtcq8JAFmAkIF22Isxfenc/QLY1c4rnVpVdvOLP2p2CosGB1HMKIv9usrsfCiHvcpzRGHlQKr1UMoavUUOrwGC77lTqUqyptxx7XAYpN3zVBl3EAzm5hxO+3bzZxx5AYUm31NE+mMtP5tM3Ngj3s21D1U80iPM1mnfQGBQvkG0VzWYYXFaWtb3UJeGGNyp2zhPX9/KnpNgxPksEwQ7bbfOpYvSK5HWqcPb365xZHCbHnSGnh37+uS/0P+zJANY+NAVSs9Dv/UOwbBoAo0/qzExH8HmBk2Bi8c8S9jrB9k51ghKwe/jPXB0nGeaxS3iqxk2j2rqXjHkUXQnH8ECY88xpSc=")
                .inventoryClickAction(event -> {
                    if(!(event.getWhoClicked() instanceof Player player)) {
                        return;
                    }

                    changePage(player, currentPage + 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 1f, 1f);
                    event.setCancelled(true);
                })
                .build());
    }

    private void changePage(Player player, int count) {
        currentPage = count;
        Inventory page = inventories.get(currentPage);
        player.openInventory(page);
    }
}
