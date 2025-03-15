package ru.sema1ary.vedrocraftapi.player.title;

import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import ru.sema1ary.vedrocraftapi.player.PlayerUtil;

import java.time.Duration;

@SuppressWarnings("unused")
public interface TitleInterface {
    default void showTitle(Player player, String title, String subtitle) {
        PlayerUtil.accept(player, player1 -> player1.showTitle(Title.title(
                        PlayerUtil.getMiniMessage().deserialize(title),
                        PlayerUtil.getMiniMessage().deserialize(subtitle)
                )
        ));
    }

    default void showTitle(Player player, String title, String subtitle, long fadeIn, long stay, long fadeOut) {
        PlayerUtil.accept(player, player1 -> player1.showTitle(Title.title(
                PlayerUtil.getMiniMessage().deserialize(title),
                PlayerUtil.getMiniMessage().deserialize(subtitle),
                Title.Times.times(
                        Duration.ofSeconds(fadeIn),
                        Duration.ofSeconds(stay),
                        Duration.ofSeconds(fadeOut)

                )
        )));
    }

    default void showInfinityTitle(Player player, String title, String subtitle) {
        PlayerUtil.accept(player, player1 -> player1.showTitle(Title.title(
                PlayerUtil.getMiniMessage().deserialize(title),
                PlayerUtil.getMiniMessage().deserialize(subtitle),
                Title.Times.times(
                        Duration.ofSeconds(Long.MIN_VALUE),
                        Duration.ofSeconds(Long.MAX_VALUE),
                        Duration.ofSeconds(Long.MIN_VALUE)

                )
        )));
    }

    default void showInfinityTitle(Player player, String title, String subtitle, long fadeIn, long fadeOut) {
        PlayerUtil.accept(player, player1 -> player1.showTitle(Title.title(
                PlayerUtil.getMiniMessage().deserialize(title),
                PlayerUtil.getMiniMessage().deserialize(subtitle),
                Title.Times.times(
                        Duration.ofSeconds(fadeIn),
                        Duration.ofSeconds(Long.MAX_VALUE),
                        Duration.ofSeconds(fadeOut)

                )
        )));
    }

    default void resetTitle(Player player) {
        PlayerUtil.accept(player, Player::resetTitle);
    }
}
