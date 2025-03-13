package ru.sema1ary.vedrocraftapi.player;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
@SuppressWarnings("unused")
public class PlayerUtil {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public void sendMessage(Player player, @NonNull String message) {
        if(player == null || !player.isOnline()) {
            return;
        }

        player.sendMessage(miniMessage.deserialize(message));
    }

    public void sendMessage(@NonNull String username, @NonNull String message) {
        Player player = Bukkit.getPlayer(username);

        if(player == null || !player.isOnline()) {
            return;
        }

        player.sendMessage(miniMessage.deserialize(message));
    }
}
