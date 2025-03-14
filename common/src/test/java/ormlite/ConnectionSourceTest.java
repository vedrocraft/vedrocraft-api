package ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sema1ary.vedrocraftapi.BaseCommons;
import ru.sema1ary.vedrocraftapi.ormlite.ConnectionSourceUtil;

import java.nio.file.Paths;
import java.sql.SQLException;

public class ConnectionSourceTest implements BaseCommons {
    @Test
    void daoFinderTest() {
        ConnectionSourceUtil.connectNoSQLDatabase(String.valueOf(Paths.get("src/test/resources/database.sqlite")),
                TestModel.class);

        Dao<TestModel, Long> dao = DaoManager.lookupDao(ConnectionSourceUtil.getConnectionSource(), TestModel.class);

        try {
            dao.createOrUpdate(TestModel.builder().build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(dao.queryForAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotNull(ConnectionSourceUtil.getConnectionSource());
    }
}
