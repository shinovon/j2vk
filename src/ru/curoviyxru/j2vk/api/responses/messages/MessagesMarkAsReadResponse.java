package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesMarkAsReadResponse extends VKResponse {

    int response;

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        response = json.optInt("response");

        return this;
    }
}
