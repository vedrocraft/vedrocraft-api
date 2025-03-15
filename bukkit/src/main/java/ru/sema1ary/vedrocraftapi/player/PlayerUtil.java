package ru.sema1ary.vedrocraftapi.player;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import ru.sema1ary.vedrocraftapi.player.message.MessageInterface;
import ru.sema1ary.vedrocraftapi.player.teleport.TeleportInterface;
import ru.sema1ary.vedrocraftapi.player.title.TitleInterface;

import java.util.function.Consumer;

@UtilityClass
@SuppressWarnings("unused")
public class PlayerUtil implements MessageInterface, TeleportInterface, TitleInterface {
    @Getter
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public void accept(Player player, Consumer<Player> consumer) {
        if(player != null && player.isOnline()) {
            consumer.accept(player);
        }
    }
}
