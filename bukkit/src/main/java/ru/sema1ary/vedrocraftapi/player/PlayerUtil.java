package ru.sema1ary.vedrocraftapi.player;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

@UtilityClass
@SuppressWarnings("unused")
public class PlayerUtil {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public void sendMessage(Player player, @NonNull String message) {
        accept(player, player1 -> player1.sendMessage(miniMessage.deserialize(message)));
    }

    public void sendMessage(@NonNull String username, @NonNull String message) {
        Player player = Bukkit.getPlayer(username);
        accept(player, player1 -> player1.sendMessage(miniMessage.deserialize(message)));
    }

    public void teleportAsync(Player player, @NonNull Location location) {
        accept(player, player1 -> player1.teleportAsync(location));
    }

    public void teleportAsync(String username, @NonNull Location location) {
        Player player = Bukkit.getPlayer(username);
        accept(player, player1 -> player1.teleportAsync(location));
    }

    private void accept(Player player, Consumer<Player> consumer) {
        if(player != null && player.isOnline()) {
            consumer.accept(player);
        }
    }
}
