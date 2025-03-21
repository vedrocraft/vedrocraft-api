package ru.sema1ary.vedrocraftapi.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.LiteCommandsBuilder;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolverBase;
import dev.rollczi.litecommands.schematic.SchematicFormat;
import dev.rollczi.litecommands.velocity.LiteVelocityFactory;
import dev.rollczi.litecommands.velocity.LiteVelocityMessages;
import dev.rollczi.litecommands.velocity.LiteVelocitySettings;
import lombok.NonNull;

@SuppressWarnings("all")
public class LiteCommandBuilder {
    private final LiteCommandsBuilder<CommandSource, LiteVelocitySettings, ?> builder;

    public LiteCommandBuilder(ProxyServer proxyServer) {
        this.builder = LiteVelocityFactory.builder(proxyServer);

        builder.settings(settings -> settings.nativePermissions(true))
                .message(LiteVelocityMessages.PLAYER_NOT_FOUND, "&cЭтот игрок не найден.")
                .message(LiteVelocityMessages.MISSING_PERMISSIONS, "&cУ вас нет прав.")
                .message(LiteVelocityMessages.INVALID_USAGE, "&cНеверное использование!")
                .message(LiteVelocityMessages.INVALID_NUMBER, "&cНеверное число.")
                .message(LiteVelocityMessages.INSTANT_INVALID_FORMAT, "&cНеверный формат даты.")
                .message(LiteVelocityMessages.UUID_INVALID_FORMAT, "&cНеверный формат UUID.")
                .message(LiteVelocityMessages.SERVER_NOT_FOUND, "&cСервер не найден.")
                .schematicGenerator(SchematicFormat.angleBrackets());
    }

    public LiteCommandBuilder commands(@NonNull Object... commands) {
        builder.commands(commands);
        return this;
    }

    public <T> LiteCommandBuilder argument(@NonNull Class<T> argumentClass,
                                           @NonNull ArgumentResolverBase<CommandSource, T> argumentResolver) {
        builder.argument(argumentClass, argumentResolver);
        return this;
    }

    public static LiteCommandBuilder builder(ProxyServer proxy) {
        return new LiteCommandBuilder(proxy);
    }

    public LiteCommands<CommandSource> build() {
        return builder.build();
    }
}
