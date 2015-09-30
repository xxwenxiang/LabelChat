package com.ekuater.labelchat.coreservice.litestrangers.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.ekuater.labelchat.coreservice.litestrangers.dao.DBLiteStranger;

import com.ekuater.labelchat.coreservice.litestrangers.dao.DBLiteStrangerDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DBLiteStrangerSession extends AbstractDaoSession {

    private final DaoConfig dBLiteStrangerDaoConfig;

    private final DBLiteStrangerDao dBLiteStrangerDao;

    public DBLiteStrangerSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dBLiteStrangerDaoConfig = daoConfigMap.get(DBLiteStrangerDao.class).clone();
        dBLiteStrangerDaoConfig.initIdentityScope(type);

        dBLiteStrangerDao = new DBLiteStrangerDao(dBLiteStrangerDaoConfig, this);

        registerDao(DBLiteStranger.class, dBLiteStrangerDao);
    }
    
    public void clear() {
        dBLiteStrangerDaoConfig.getIdentityScope().clear();
    }

    public DBLiteStrangerDao getDBLiteStrangerDao() {
        return dBLiteStrangerDao;
    }

}
