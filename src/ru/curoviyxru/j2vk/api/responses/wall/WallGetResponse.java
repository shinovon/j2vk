package ru.curoviyxru.j2vk.api.responses.wall;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Post;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class WallGetResponse extends VKExtendedResponse {

    public Post[] items;

    public boolean hasItems() {
        return items != null && items.length > 0;
    }

    public VKSerializableObject deserialize(JSONObject obj) {
        super.deserialize(obj);
        if (obj == null) {
            return this;
        }

        JSONObject json = obj.optJSONObject("response");
        if (json != null) {
            JSONArray array = json.optJSONArray("items");
            if (array != null) {
                items = new Post[array.length()];
                for (int i = 0; i < items.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        items[i] = (Post) new Post().deserialize(obj1);
                    }
                }
            }
        }

        return this;
    }
}
