package ru.sema1ary.vedrocraftapi.player.teleport;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ru.sema1ary.vedrocraftapi.player.PlayerUtil;
import ru.sema1ary.vedrocraftapi.serialization.LocationSerializer;

@SuppressWarnings("unused")
public interface TeleportInterface {
    default void teleport(Player player, @NonNull Location location) {
        PlayerUtil.accept(player, player1 -> player1.teleport(location));
    }

    default void teleport(@NonNull String username, @NonNull Location location) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.teleport(location));
    }

    default void teleport(Player player, @NonNull String location) {
        PlayerUtil.accept(player, player1 -> player1.teleport(LocationSerializer.deserialize(location)));
    }

    default void teleport(@NonNull String username, @NonNull String location) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.teleport(LocationSerializer.deserialize(location)));
    }

    default void teleportAsync(Player player, @NonNull Location location) {
        PlayerUtil.accept(player, player1 -> player1.teleportAsync(location));
    }

    default void teleportAsync(@NonNull String username, @NonNull Location location) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.teleportAsync(location));
    }

    default void teleportAsync(Player player, @NonNull String location) {
        PlayerUtil.accept(player, player1 -> player1.teleportAsync(LocationSerializer.deserialize(location)));
    }

    default void teleportAsync(@NonNull String username, @NonNull String location) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.teleportAsync(LocationSerializer.deserialize(location)));
    }
}
