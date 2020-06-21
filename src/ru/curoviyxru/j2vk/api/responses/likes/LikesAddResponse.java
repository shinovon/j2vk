package ru.curoviyxru.j2vk.api.responses.likes;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class LikesAddResponse extends VKResponse {

    public int likes = -1;

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            likes = response.optInt("likes", -1);
        }
        return this;
    }
}
