package ru.curoviyxru.j2vk.api.responses.wall;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class WallCreateCommentResponse extends VKResponse {

    public int commentId = -1;

    public boolean isSuccessful() {
        return commentId != -1;
    }

    public VKSerializableObject deserialize(JSONObject o) {
        super.deserialize(o);
        if (o == null) {
            return this;
        }

        JSONObject obj = o.optJSONObject("response");
        if (obj != null) commentId = obj.optInt("comment_id", -1);
        else commentId = -1;

        return this;
    }
}
