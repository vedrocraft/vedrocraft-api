package ru.sema1ary.vedrocraftapi.player;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.sema1ary.vedrocraftapi.serialization.LocationSerializer;

import java.time.Duration;
import java.util.function.Consumer;

@UtilityClass
@SuppressWarnings("unused")
public class PlayerUtil {
    @Getter
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public void sendMessage(Player player, @NonNull Component message) {
        PlayerUtil.accept(player, player1 -> player1.sendMessage(message));
    }

    public void sendMessage(CommandSender player, @NonNull Component message) {
        PlayerUtil.accept((Player) player, player1 -> player1.sendMessage(message));
    }

    public void sendMessage(@NonNull String username, @NonNull Component message) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.sendMessage(message));
    }

    public void sendMessage(Player player, @NonNull String message) {
        PlayerUtil.accept(player, player1 -> player1.sendMessage(
                PlayerUtil.getMiniMessage().deserialize(message)
        ));
    }

    public void sendMessage(CommandSender player, @NonNull String message) {
        PlayerUtil.accept((Player) player, player1 -> player1.sendMessage(
                PlayerUtil.getMiniMessage().deserialize(message)
        ));
    }

    public void sendMessage(@NonNull String username, @NonNull String message) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.sendMessage(
                PlayerUtil.getMiniMessage().deserialize(message)
        ));
    }

    public void sendMessageWithPAPI(Player player, @NonNull Component message) {
        sendMessage(player, PlayerUtil.getMiniMessage().deserialize(PlaceholderAPI
                .setPlaceholders(player, PlayerUtil.getMiniMessage().serialize(message))));
    }

    public void sendMessageWithPAPI(CommandSender player, @NonNull Component message) {
        sendMessage(player, PlayerUtil.getMiniMessage().deserialize(PlaceholderAPI
                .setPlaceholders((Player) player, PlayerUtil.getMiniMessage().serialize(message))));
    }

    public void sendMessageWithPAPI(@NonNull String username, @NonNull Component message) {
        Player player = Bukkit.getPlayer(username);
        sendMessage(player, PlayerUtil.getMiniMessage().deserialize(PlaceholderAPI
                .setPlaceholders(player, PlayerUtil.getMiniMessage().serialize(message))));
    }

    public void sendMessageWithPAPI(Player player, @NonNull String message) {
        sendMessage(player, PlaceholderAPI
                .setPlaceholders(player, message));
    }

    public void sendMessageWithPAPI(CommandSender player, @NonNull String message) {
        sendMessage(player, PlaceholderAPI
                .setPlaceholders((Player) player, message));
    }

    public void sendMessageWithPAPI(@NonNull String username, @NonNull String message) {
        Player player = Bukkit.getPlayer(username);
        sendMessage(player, PlaceholderAPI
                .setPlaceholders(player, message));
    }

    public void teleportAsync(Player player, @NonNull Location location) {
        PlayerUtil.accept(player, player1 -> player1.teleportAsync(location));
    }

    public void teleportAsync(@NonNull String username, @NonNull Location location) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.teleportAsync(location));
    }

    public void teleportAsync(Player player, @NonNull String location) {
        PlayerUtil.accept(player, player1 -> player1.teleportAsync(LocationSerializer.deserialize(location)));
    }

    public void teleportAsync(@NonNull String username, @NonNull String location) {
        Player player = Bukkit.getPlayer(username);
        PlayerUtil.accept(player, player1 -> player1.teleportAsync(LocationSerializer.deserialize(location)));
    }

    public void showTitle(Player player, String title, String subtitle) {
        PlayerUtil.accept(player, player1 -> player1.showTitle(Title.title(
                        PlayerUtil.getMiniMessage().deserialize(title),
                        PlayerUtil.getMiniMessage().deserialize(subtitle)
                )
        ));
    }

    public void showTitle(Player player, String title, String subtitle, long fadeIn, long stay, long fadeOut) {
        PlayerUtil.accept(player, player1 -> player1.showTitle(Title.title(
                PlayerUtil.getMiniMessage().deserialize(title),
                PlayerUtil.getMiniMessage().deserialize(subtitle),
                Title.Times.times(
                        Duration.ofMillis(fadeIn),
                        Duration.ofMillis(stay),
                        Duration.ofMillis(fadeOut)

                )
        )));
    }

    public void showInfinityTitle(Player player, String title, String subtitle) {
        PlayerUtil.accept(player, player1 -> player1.showTitle(Title.title(
                PlayerUtil.getMiniMessage().deserialize(title),
                PlayerUtil.getMiniMessage().deserialize(subtitle),
                Title.Times.times(
                        Duration.ofMillis(60),
                        Duration.ofDays(120),
                        Duration.ofMillis(60)
                )
        )));
    }

    public void showInfinityTitle(Player player, String title, String subtitle, long fadeIn, long fadeOut) {
        PlayerUtil.accept(player, player1 -> player1.showTitle(Title.title(
                PlayerUtil.getMiniMessage().deserialize(title),
                PlayerUtil.getMiniMessage().deserialize(subtitle),
                Title.Times.times(
                        Duration.ofMillis(fadeIn),
                        Duration.ofDays(120),
                        Duration.ofMillis(fadeOut)
                )
        )));
    }

    public void resetTitle(Player player) {
        PlayerUtil.accept(player, Player::resetTitle);
    }

    private void accept(Player player, Consumer<Player> consumer) {
        if(player != null && player.isOnline()) {
            consumer.accept(player);
        }
    }
}
