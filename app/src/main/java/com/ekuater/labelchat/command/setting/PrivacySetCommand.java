package com.ekuater.labelchat.command.setting;

import com.ekuater.labelchat.command.CommandUrl;
import com.ekuater.labelchat.command.SessionCommand;
import org.json.JSONException;

/**
 * @author LinYong
 */
public class PrivacySetCommand extends SessionCommand {

    private static final String URL = CommandUrl.SETTING_PRIVACY;

    public PrivacySetCommand() {
        super();
        setUrl(URL);
    }

    public PrivacySetCommand(String session) {
        super(session);
        setUrl(URL);
    }

    public void putParamPrivacySet(String keyword,String value) {
        putParam(keyword,value);
    }
    public void putParamUserId(String keyword) {
        putParam("userId", keyword);
    }
    public static class CommandResponse extends SessionCommand.CommandResponse {

        public CommandResponse(String response) throws JSONException {
            super(response);
        }
    }
}
