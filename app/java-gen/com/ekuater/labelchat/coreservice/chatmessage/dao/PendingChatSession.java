package com.ekuater.labelchat.coreservice.chatmessage.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.ekuater.labelchat.coreservice.chatmessage.dao.PendingChat;

import com.ekuater.labelchat.coreservice.chatmessage.dao.PendingChatDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class PendingChatSession extends AbstractDaoSession {

    private final DaoConfig pendingChatDaoConfig;

    private final PendingChatDao pendingChatDao;

    public PendingChatSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        pendingChatDaoConfig = daoConfigMap.get(PendingChatDao.class).clone();
        pendingChatDaoConfig.initIdentityScope(type);

        pendingChatDao = new PendingChatDao(pendingChatDaoConfig, this);

        registerDao(PendingChat.class, pendingChatDao);
    }
    
    public void clear() {
        pendingChatDaoConfig.getIdentityScope().clear();
    }

    public PendingChatDao getPendingChatDao() {
        return pendingChatDao;
    }

}