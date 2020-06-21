package ru.curoviyxru.j2vk.api.responses.likes;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class LikesIsLikedResponse extends VKResponse {

    boolean liked, copied;

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            liked = fromInteger(response.optInt("liked"));
            copied = fromInteger(response.optInt("copied"));
        }
        return this;
    }
}
