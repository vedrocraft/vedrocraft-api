package ru.sema1ary.vedrocraftapi.player.message;

import lombok.NonNull;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.sema1ary.vedrocraftapi.player.PlayerUtil;

@SuppressWarnings("unused")
public interface MessageInterface {
    default void sendMessage(Player player, @NonNull Component message) {
        PlayerUtil.accept(player, player1 -> player1.sendMessage(message));
    }

    default void sendMessage(CommandSender player, @NonNull Component message) {
        PlayerUtil.accept((Player) player, player1 -> player1.sendMessage(message));
    }

    default void sendMessage(@NonNull String username, @NonNull Component message) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.sendMessage(message));
    }

    default void sendMessage(Player player, @NonNull String message) {
        PlayerUtil.accept(player, player1 -> player1.sendMessage(
                PlayerUtil.getMiniMessage().deserialize(message)
        ));
    }

    default void sendMessage(CommandSender player, @NonNull String message) {
        PlayerUtil.accept((Player) player, player1 -> player1.sendMessage(
                PlayerUtil.getMiniMessage().deserialize(message)
        ));
    }

    default void sendMessage(@NonNull String username, @NonNull String message) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.sendMessage(
                PlayerUtil.getMiniMessage().deserialize(message)
        ));
    }

    default void sendMessageWithPAPI(Player player, @NonNull Component message) {
        sendMessage(player, PlayerUtil.getMiniMessage().deserialize(PlaceholderAPI
                .setPlaceholders(player, PlayerUtil.getMiniMessage().serialize(message))));
    }

    default void sendMessageWithPAPI(CommandSender player, @NonNull Component message) {
        sendMessage(player, PlayerUtil.getMiniMessage().deserialize(PlaceholderAPI
                .setPlaceholders((Player) player, PlayerUtil.getMiniMessage().serialize(message))));
    }

    default void sendMessageWithPAPI(@NonNull String username, @NonNull Component message) {
        Player player = Bukkit.getPlayer(username);
        sendMessage(player, PlayerUtil.getMiniMessage().deserialize(PlaceholderAPI
                .setPlaceholders(player, PlayerUtil.getMiniMessage().serialize(message))));
    }

    default void sendMessageWithPAPI(Player player, @NonNull String message) {
        sendMessage(player, PlaceholderAPI
                .setPlaceholders(player, message));
    }

    default void sendMessageWithPAPI(CommandSender player, @NonNull String message) {
        sendMessage(player, PlaceholderAPI
                .setPlaceholders((Player) player, message));
    }

    default void sendMessageWithPAPI(@NonNull String username, @NonNull String message) {
        Player player = Bukkit.getPlayer(username);
        sendMessage(player, PlaceholderAPI
                .setPlaceholders(player, message));
    }
}
