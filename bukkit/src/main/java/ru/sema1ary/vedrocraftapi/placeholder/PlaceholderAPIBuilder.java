package ru.sema1ary.vedrocraftapi.placeholder;

import lombok.NonNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PlaceholderAPIBuilder {
    private final List<PlaceholderExpansion> placeholderList = new ArrayList<>();

    public PlaceholderAPIBuilder placeholder(@NonNull PlaceholderExpansion placeholderExpansion) {
        placeholderList.add(placeholderExpansion);
        return this;
    }

    public static PlaceholderAPIBuilder builder() {
        return new PlaceholderAPIBuilder();
    }

    public void build() {
        placeholderList.forEach(placeholderExpansion -> {
            if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                placeholderExpansion.register();
            }
        });
    }
}
