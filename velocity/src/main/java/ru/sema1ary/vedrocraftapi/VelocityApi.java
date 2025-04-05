package ru.sema1ary.vedrocraftapi;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.SneakyThrows;
import ru.sema1ary.vedrocraftapi.listener.JoinListener;
import ru.sema1ary.vedrocraftapi.ormlite.ConnectionSourceUtil;
import ru.sema1ary.vedrocraftapi.ormlite.DatabaseUtil;
import ru.sema1ary.vedrocraftapi.ormlite.model.User;
import ru.sema1ary.vedrocraftapi.service.ConfigService;
import ru.sema1ary.vedrocraftapi.service.ServiceManager;
import ru.sema1ary.vedrocraftapi.service.impl.ConfigServiceImpl;
import ru.sema1ary.vedrocraftapi.service.user.UserService;

@Plugin(id = "vedrocraft-api", name = "vedrocraft-api", version = "1.0", authors = {"sema1ary"})
public class VelocityApi implements BaseCommons {

    private final ProxyServer server;

    @Inject
    public VelocityApi(ProxyServer server) {
        this.server = server;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        ServiceManager.registerService(ConfigService.class, new ConfigServiceImpl("vedrocraft-api"));

        DatabaseUtil.initConnectionSource(
                "vedrocraft-api",
                getService(ConfigService.class),
                User.class
        );

        server.getEventManager().register(this, new JoinListener(
           getService(UserService.class)
        ));
    }

    @Subscribe
    @SneakyThrows
    public void onProxyShutdown(ProxyShutdownEvent event) {
        ConnectionSourceUtil.closeConnection(true);
    }
}
