package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetLongPollServerResponse extends VKResponse {

    public String server, key;
    public int ts;

    public boolean isSuccessful() {
        return super.isSuccessful() && hasServer() && hasKey() && hasTS();
    }

    public boolean hasServer() {
        return !isEmpty(server);
    }

    public boolean hasKey() {
        return !isEmpty(key);
    }

    public boolean hasTS() {
        return ts != 0;
    }

    public VKSerializableObject deserialize(JSONObject json1) {
        super.deserialize(json1);
        if (json1 == null) {
            return this;
        }

        JSONObject json = json1.optJSONObject("response");
        if (json != null) {
            ts = json.optInt("ts");
            server = json.optString("server");
            key = json.optString("key");
        }

        return this;
    }
}
