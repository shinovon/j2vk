package ru.curoviyxru.j2vk.api.responses.newsfeed;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Post;
import ru.curoviyxru.j2vk.api.objects.VKObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class NewsfeedGetResponse extends VKExtendedResponse {

    public Post[] items;
    public String next_from;

    public boolean hasNextFrom() {
        return !VKObject.isEmpty(next_from);
    }

    public boolean hasItems() {
        return items != null && items.length > 0;
    }

    public VKSerializableObject deserialize(JSONObject orig) {
        super.deserialize(orig);
        if (orig == null) {
            return this;
        }

        JSONObject json = orig.optJSONObject("response");
        if (json != null) {
            next_from = json.optString("next_from");
            JSONArray array = json.optJSONArray("items");
            if (array != null) {
                items = new Post[array.length()];
                for (int i = 0; i < items.length; i++) {
                    JSONObject jsonobjj = array.optJSONObject(i);
                    if (jsonobjj != null) {
                        items[i] = (Post) new Post().deserialize(jsonobjj);
                    }
                }
            }
        }

        return this;
    }
}
