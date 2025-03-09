package ru.sema1ary.vedrocraftapi.service.util.command;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.LiteCommandsBuilder;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolverBase;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.LiteBukkitMessages;
import dev.rollczi.litecommands.schematic.SchematicFormat;
import org.bukkit.command.CommandSender;
import ru.sema1ary.vedrocraftapi.service.service.ConfigService;

@SuppressWarnings("all")
public class LiteCommandUtil {
    private LiteCommandsBuilder<CommandSender, ?, ?> builder;

    public LiteCommands<CommandSender> create(Object... commands) {
        return builder.build();
    }

    public void initBuilder(ConfigService configService, Object... commands) {
        Object[] newCommands = {new ReloadCommand(configService), commands};

        builder = LiteBukkitFactory.builder()
                .settings(settings -> settings
                        .fallbackPrefix("vedrocraft")
                        .nativePermissions(true)
                )
                .commands(newCommands)
                .message(LiteBukkitMessages.INVALID_USAGE, "&cНеверное использование!")
                .message(LiteBukkitMessages.PLAYER_ONLY, "&cЭта команда только для игроков!")
                .message(LiteBukkitMessages.PLAYER_NOT_FOUND, "&cЭтот игрок не найден.")
                .message(LiteBukkitMessages.MISSING_PERMISSIONS, "&cУ вас нет прав.")
                .schematicGenerator(SchematicFormat.angleBrackets());
    }

    public <T> void registerArgument(Class<T> argumentClass, ArgumentResolverBase<CommandSender, T> argumentResolver) {
        builder.argument(argumentClass, argumentResolver);
    }
}
