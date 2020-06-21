package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesSetActivityResponse extends VKResponse {

    public boolean successful;

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        successful = json.optInt("response") == 1;

        return this;
    }
}
