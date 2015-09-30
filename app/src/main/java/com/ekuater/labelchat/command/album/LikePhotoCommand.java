package com.ekuater.labelchat.command.album;

import com.ekuater.labelchat.command.CommandFields;
import com.ekuater.labelchat.command.CommandUrl;
import com.ekuater.labelchat.command.UserCommand;

import org.json.JSONException;

/**
 * Created by Leo on 2015/3/19.
 *
 * @author LinYong
 */
public class LikePhotoCommand extends UserCommand {

    private static final String URL = CommandUrl.ALBUM_LIKE_PHOTO;

    public LikePhotoCommand() {
        super();
        setUrl(URL);
    }

    public LikePhotoCommand(String session, String userId) {
        super(session, userId);
        setUrl(URL);
    }

    public void putParamPhotoId(String photoId) {
        putParam(CommandFields.Album.PHOTO_ID, photoId);
    }

    public static class CommandResponse extends UserCommand.CommandResponse {

        public CommandResponse(String response) throws JSONException {
            super(response);
        }
    }
}
