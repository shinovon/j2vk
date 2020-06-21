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
public class WallGetCommentsResponse extends VKExtendedResponse {

    int count;
    public Comment[] items;
    int current_level_count;
    boolean can_post, show_reply_button, groups_can_post;

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            count = response.optInt("count");
            current_level_count = response.optInt("current_level_count");
            can_post = response.optBoolean("can_post");
            show_reply_button = response.optBoolean("show_reply_button");
            groups_can_post = response.optBoolean("groups_can_post");

            JSONArray array = response.optJSONArray("items");
            if (array != null) {
                items = new Comment[array.length()];
                for (int i = 0; i < items.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        items[i] = (Comment) new Comment().deserialize(obj1);
                    }
                }
            }
        }

        return this;
    }

    public boolean hasItems() {
        return items != null && items.length > 0;
    }
}
