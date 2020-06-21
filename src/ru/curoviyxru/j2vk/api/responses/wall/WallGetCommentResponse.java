package ru.curoviyxru.j2vk.api.responses.wall;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Comment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class WallGetCommentResponse extends VKExtendedResponse {

    public Comment[] comments;
    public int count;

    public final boolean hasComments() {
        return comments != null && comments.length > 0;
    }

    public final Comment getComment() {
        return hasComments() ? comments[0] : null;
    }

    public final boolean hasComment() {
        return getComment() != null;
    }

    public final VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject obj = json.optJSONObject("response");
        if (obj != null) {
            count = obj.optInt("count");
            JSONArray array = obj.optJSONArray("items");
            if (array != null) {
                comments = new Comment[array.length()];
                for (int i = 0; i < comments.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        comments[i] = (Comment) new Comment().deserialize(obj1);
                    }
                }
            }
        }

        return this;
    }
}
