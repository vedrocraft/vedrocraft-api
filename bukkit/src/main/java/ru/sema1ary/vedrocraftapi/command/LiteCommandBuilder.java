package ru.sema1ary.vedrocraftapi.command;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.LiteCommandsBuilder;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolverBase;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.LiteBukkitMessages;
import dev.rollczi.litecommands.bukkit.LiteBukkitSettings;
import dev.rollczi.litecommands.schematic.SchematicFormat;
import org.bukkit.command.CommandSender;
import ru.sema1ary.vedrocraftapi.service.ConfigService;

@SuppressWarnings("all")
public class LiteCommandBuilder {
    private ConfigService configService;
    private LiteCommandsBuilder<CommandSender, LiteBukkitSettings, ?> builder = LiteBukkitFactory.builder();

    public LiteCommandBuilder(ConfigService configService) {
        this.configService = configService;

        builder.settings(settings -> settings.fallbackPrefix("vedrocraft")
                        .nativePermissions(true))
                .message(LiteBukkitMessages.INVALID_USAGE, "&cНеверное использование!")
                .message(LiteBukkitMessages.PLAYER_ONLY, "&cЭта команда только для игроков!")
                .message(LiteBukkitMessages.PLAYER_NOT_FOUND, "&cЭтот игрок не найден.")
                .message(LiteBukkitMessages.MISSING_PERMISSIONS, "&cУ вас нет прав.")
                .message(LiteBukkitMessages.WORLD_NOT_EXIST, "&cЭтот мир не существует.")
                .message(LiteBukkitMessages.UUID_INVALID_FORMAT, "&cНеверный формат UUID.")
                .message(LiteBukkitMessages.LOCATION_INVALID_FORMAT, "&cНеверный формат локации.")
                .message(LiteBukkitMessages.INVALID_NUMBER, "&cНеверное число.")
                .message(LiteBukkitMessages.INSTANT_INVALID_FORMAT, "&cНеверный формат даты.")
                .schematicGenerator(SchematicFormat.angleBrackets());
    }

    public LiteCommandBuilder commands(Object... commands) {
        builder.commands(commands);
        return this;
    }

    public <T> LiteCommandBuilder argument(Class<T> argumentClass,
                                           ArgumentResolverBase<CommandSender, T> argumentResolver) {
        builder.argument(argumentClass, argumentResolver);
        return this;
    }

    public static LiteCommandBuilder builder(ConfigService configService) {
        return new LiteCommandBuilder(configService);
    }

    public LiteCommands<CommandSender> build() {
        return builder.build();
    }
}
