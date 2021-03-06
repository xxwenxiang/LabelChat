package com.ekuater.labelchat.coreservice.litestrangers.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBLITE_STRANGER.
 */
public class DBLiteStranger {

    private Long id;
    /** Not-null value. */
    private String userId;
    /** Not-null value. */
    private String labelCode;
    private String nickname;
    private String avatarThumb;
    private Long cacheTime;

    public DBLiteStranger() {
    }

    public DBLiteStranger(Long id) {
        this.id = id;
    }

    public DBLiteStranger(Long id, String userId, String labelCode, String nickname, String avatarThumb, Long cacheTime) {
        this.id = id;
        this.userId = userId;
        this.labelCode = labelCode;
        this.nickname = nickname;
        this.avatarThumb = avatarThumb;
        this.cacheTime = cacheTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getUserId() {
        return userId;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** Not-null value. */
    public String getLabelCode() {
        return labelCode;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarThumb() {
        return avatarThumb;
    }

    public void setAvatarThumb(String avatarThumb) {
        this.avatarThumb = avatarThumb;
    }

    public Long getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(Long cacheTime) {
        this.cacheTime = cacheTime;
    }

}
