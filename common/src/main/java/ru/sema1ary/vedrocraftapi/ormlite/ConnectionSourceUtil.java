package ru.sema1ary.vedrocraftapi.ormlite;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.sema1ary.vedrocraftapi.ormlite.dao.DaoCreatorUtil;
import ru.sema1ary.vedrocraftapi.service.ServiceManager;

@UtilityClass
@SuppressWarnings("unused")
public class ConnectionSourceUtil {
    @SneakyThrows
    public JdbcPooledConnectionSource connectSQL(String host, String database, String user,
                                                 String pass, @NonNull Class<?>... modelClasses) {
        JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(
                "jdbc:mariadb://" + host + "/" + database + "?autoReconnect=true");
        setUpTheConnection(connectionSource, user, pass);
        createModelDaoAndTable(connectionSource, modelClasses);
        return connectionSource;
    }

    @SneakyThrows
    public JdbcPooledConnectionSource connectSQLWithSSL(String host, String database, String user,
                                                 String pass, @NonNull Class<?>... modelClasses) {
        JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(
                "jdbc:mariadb://" + host + "/" + database + "?sslMode=trust&autoReconnect=true");
        setUpTheConnection(connectionSource, user, pass);
        createModelDaoAndTable(connectionSource, modelClasses);
        return connectionSource;
    }

    @SneakyThrows
    public JdbcPooledConnectionSource connectNoSQLDatabase(@NonNull String filePath,
                                                           @NonNull Class<?>... modelClasses) {
        JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(
                "jdbc:sqlite:" + filePath);
        createModelDaoAndTable(connectionSource, modelClasses);
        return connectionSource;
    }

    private void setUpTheConnection(@NonNull JdbcPooledConnectionSource connectionSource, String user, String pass) {
        connectionSource.setUsername(user);
        connectionSource.setPassword(pass);
        connectionSource.setMaxConnectionsFree(5);
    }

    private void createModelDaoAndTable(@NonNull JdbcPooledConnectionSource connectionSource, @NonNull Class<?>... modelClasses) {
        TableCreatorUtil.create(connectionSource, modelClasses);
        DaoCreatorUtil.create(connectionSource, modelClasses);
    }

    @SneakyThrows
    public void closeConnection(boolean shouldDisableServices, JdbcPooledConnectionSource connectionSource) {
        if(shouldDisableServices) ServiceManager.disableServices();
        if(connectionSource != null) connectionSource.close();
    }
}
