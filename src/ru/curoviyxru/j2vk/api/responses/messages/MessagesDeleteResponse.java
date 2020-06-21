package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesDeleteResponse extends VKResponse {

    JSONObject msgObj;

    public boolean hasDeleted(int id) {
        return msgObj != null
                && (msgObj.optInt(String.valueOf(id)) == 1
                || msgObj.optInt("response") == 1
                || (msgObj.optJSONObject("response") != null && msgObj.optJSONObject("response").optInt(String.valueOf(id)) == 1));
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        msgObj = json;

        return this;
    }
}
