package ru.curoviyxru.j2vk.api.responses.wall;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class WallRepostResponse extends VKResponse {

    public boolean success;
    public int post_id, reposts_count, likes_count;

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            success = fromInteger(response.optInt("success"));
            post_id = response.optInt("post_id");
            reposts_count = response.optInt("reposts_count");
            likes_count = response.optInt("likes_count");
        }
        return this;
    }
}
