package com.ekuater.labelchat.command.following;

import com.ekuater.labelchat.command.CommandFields;
import com.ekuater.labelchat.command.CommandUrl;
import com.ekuater.labelchat.command.UserCommand;

import org.json.JSONException;

/**
 * Created by Label on 2015/3/12.
 */
public class FollowingCancelCommand extends UserCommand{

    private static final String URL = CommandUrl.UN_FOLLOWING;

    public FollowingCancelCommand (){
        super();
        setUrl(URL);
    }

    public FollowingCancelCommand (String session, String userId){
        super(session,userId);
        setUrl(URL);
    }

    public void putParamFollowId(String followUserId){
        putParam(CommandFields.Following.FOLLOW_USER_ID,followUserId);
    }

    public static class CommandResponse extends UserCommand.CommandResponse{

        public CommandResponse(String response) throws JSONException {
            super(response);
        }
    }

}
