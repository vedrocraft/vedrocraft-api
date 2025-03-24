package ru.sema1ary.vedrocraftapi.player;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
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
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public void sendMessage(Player player, @NonNull Component message) {
        PlayerUtil.accept(player, (Consumer<Player>) player1 -> player1.sendMessage(message));
    }

    public void sendMessage(CommandSender sender, @NonNull Component message) {
        PlayerUtil.accept(sender, player -> player.sendMessage(message));
    }

    public void sendMessage(@NonNull String username, @NonNull Component message) {
        PlayerUtil.accept(Bukkit.getPlayer(username), (Consumer<Player>) player1 -> player1.sendMessage(message));
    }

    public void sendMessage(Player player, @NonNull String message) {
        accept(player, (Consumer<Player>) player1 -> player1.sendMessage(
                miniMessage.deserialize(message)
        ));
    }

    public void sendMessage(CommandSender player, @NonNull String message) {
        accept(player, player1 -> player1.sendMessage(
                miniMessage.deserialize(message)
        ));
    }

    public void sendMessage(@NonNull String username, @NonNull String message) {
        accept(Bukkit.getPlayer(username), (Consumer<Player>) player1 -> player1.sendMessage(
                miniMessage.deserialize(message)
        ));
    }

    public void teleportAsync(Player player, @NonNull Location location) {
        accept(player, (Consumer<Player>) player1 -> player1.teleportAsync(location));
    }

    public void teleportAsync(@NonNull String username, @NonNull Location location) {
        Player player = Bukkit.getPlayer(username);
        accept(player, (Consumer<Player>) player1 -> player1.teleportAsync(location));
    }

    public void teleportAsync(Player player, @NonNull String location) {
        accept(player, (Consumer<Player>) player1 -> player1.teleportAsync(LocationSerializer.deserialize(location)));
    }

    public void teleportAsync(@NonNull String username, @NonNull String location) {
        Player player = Bukkit.getPlayer(username);
        accept(player, (Consumer<Player>) player1 -> player1.teleportAsync(LocationSerializer.deserialize(location)));
    }

    public void showTitle(Player player, String title, String subtitle) {
        accept(player, (Consumer<Player>) player1 -> player1.showTitle(Title.title(
                miniMessage.deserialize(title),
                miniMessage.deserialize(subtitle)
        )));
    }

    public void showTitle(Player player, String title, String subtitle, long fadeIn, long stay, long fadeOut) {
        accept(player, (Consumer<Player>) player1 -> player1.showTitle(Title.title(
                miniMessage.deserialize(title),
                miniMessage.deserialize(subtitle),
                Title.Times.times(
                        Duration.ofMillis(fadeIn),
                        Duration.ofMillis(stay), Duration.ofMillis(fadeOut)
                )
        )));
    }

    public void showInfinityTitle(Player player, String title, String subtitle) {
        accept(player, (Consumer<Player>) player1 -> player1.showTitle(Title.title(
                miniMessage.deserialize(title),
                miniMessage.deserialize(subtitle),
                Title.Times.times(
                        Duration.ofMillis(60),
                        Duration.ofDays(120),
                        Duration.ofMillis(60)
                )
        )));
    }

    public void showInfinityTitle(Player player, String title, String subtitle, long fadeIn, long fadeOut) {
        accept(player, (Consumer<Player>) player1 -> player1.showTitle(Title.title(
                miniMessage.deserialize(title),
                miniMessage.deserialize(subtitle),
                Title.Times.times(
                        Duration.ofMillis(fadeIn),
                        Duration.ofDays(120),
                        Duration.ofMillis(fadeOut)
                )
        )));
    }

    public void resetTitle(Player player) {
        accept(player, Player::resetTitle);
    }

    private void accept(Player player, Consumer<Player> consumer) {
        if(player != null && player.isOnline()) {
            consumer.accept(player);
        }
    }

    private void accept(CommandSender sender, Consumer<CommandSender> consumer) {
        if(sender != null) {
            consumer.accept(sender);
        }
    }
}
