package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesSendResponse extends VKResponse {

    public int response;

    public boolean isSuccessful() {
        return response != -1;
    }

    public VKSerializableObject deserialize(JSONObject o) {
        super.deserialize(o);
        if (o == null) {
            return this;
        }

        response = o.optInt("response", -1);

        return this;
    }
}
