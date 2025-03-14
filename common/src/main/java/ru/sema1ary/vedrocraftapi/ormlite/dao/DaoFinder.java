package ru.sema1ary.vedrocraftapi.ormlite.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import ru.sema1ary.vedrocraftapi.ormlite.ConnectionSourceUtil;

@SuppressWarnings("unused")
public interface DaoFinder {
    default <D extends Dao<T, ?>, T> D getDao(Class<T> daoClass) {
        return DaoManager.lookupDao(ConnectionSourceUtil.getConnectionSource(), daoClass);
    }
}
