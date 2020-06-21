package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetLongPollHistoryResponse extends VKResponse {

    public JSONArray updates;

    public boolean hasUpdates() {
        return updates != null && updates.length() > 0;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            updates = response.optJSONArray("history");
        }

        return this;
    }
}
