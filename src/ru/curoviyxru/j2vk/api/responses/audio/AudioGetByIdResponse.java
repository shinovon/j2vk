package ru.curoviyxru.j2vk.api.responses.audio;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Audio;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class AudioGetByIdResponse extends VKResponse {

    public Audio[] response;

    public boolean hasItems() {
        return response != null && response.length > 0;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONArray arr = json.optJSONArray("response");
        if (arr != null) {
            response = new Audio[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                if (obj != null) {
                    response[i] = (Audio) new Audio().deserialize(obj);
                }
            }
        }

        return this;
    }
}
