package com.ekuater.labelchat.coreservice.following.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.ekuater.labelchat.coreservice.following.dao.DBFollowerUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DBFOLLOWER_USER.
*/
public class DBFollowerUserDao extends AbstractDao<DBFollowerUser, Long> {

    public static final String TABLENAME = "DBFOLLOWER_USER";

    /**
     * Properties of entity DBFollowerUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id", TABLENAME);
        public final static Property UserId = new Property(1, String.class, "userId", false, "USER_ID", TABLENAME);
        public final static Property LabelCode = new Property(2, String.class, "labelCode", false, "LABEL_CODE", TABLENAME);
        public final static Property Nickname = new Property(3, String.class, "nickname", false, "NICKNAME", TABLENAME);
        public final static Property Avatar = new Property(4, String.class, "avatar", false, "AVATAR", TABLENAME);
        public final static Property AvatarThumb = new Property(5, String.class, "avatarThumb", false, "AVATAR_THUMB", TABLENAME);
        public final static Property Gender = new Property(6, Integer.class, "gender", false, "GENDER", TABLENAME);
    };


    public DBFollowerUserDao(DaoConfig config) {
        super(config);
    }
    
    public DBFollowerUserDao(DaoConfig config, DBFollowUserSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DBFOLLOWER_USER' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'USER_ID' TEXT NOT NULL UNIQUE ," + // 1: userId
                "'LABEL_CODE' TEXT NOT NULL UNIQUE ," + // 2: labelCode
                "'NICKNAME' TEXT," + // 3: nickname
                "'AVATAR' TEXT," + // 4: avatar
                "'AVATAR_THUMB' TEXT," + // 5: avatarThumb
                "'GENDER' INTEGER);"); // 6: gender
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DBFOLLOWER_USER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBFollowerUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUserId());
        stmt.bindString(3, entity.getLabelCode());
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(4, nickname);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(5, avatar);
        }
 
        String avatarThumb = entity.getAvatarThumb();
        if (avatarThumb != null) {
            stmt.bindString(6, avatarThumb);
        }
 
        Integer gender = entity.getGender();
        if (gender != null) {
            stmt.bindLong(7, gender);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DBFollowerUser readEntity(Cursor cursor, int offset) {
        DBFollowerUser entity = new DBFollowerUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // userId
            cursor.getString(offset + 2), // labelCode
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // nickname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // avatar
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // avatarThumb
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6) // gender
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBFollowerUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.getString(offset + 1));
        entity.setLabelCode(cursor.getString(offset + 2));
        entity.setNickname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAvatar(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAvatarThumb(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setGender(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DBFollowerUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DBFollowerUser entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
