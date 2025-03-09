package ru.sema1ary.vedrocraftapi.service.util.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.sema1ary.vedrocraftapi.service.service.ConfigService;

@RequiredArgsConstructor
public class ReloadCommand implements CommandExecutor {
    private final ConfigService configService;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                             @NotNull String[] args) {
        if(!sender.hasPermission("vedrocraftapi.reload")) {
            sender.sendMessage("&cУ вас нет прав.");
            return false;
        }

        configService.reload();
        sender.sendMessage("&aВы успешно перезапустили плагин.");

        return false;
    }
}
