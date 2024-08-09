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
public class AudioGetResponse extends VKResponse {

    public Audio[] items;
    public int count;

    public boolean hasItems() {
        return items != null && items.length > 0;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject obj = json.optJSONObject("response");
        if (obj != null) {
        	count = obj.optInt("count");
            JSONArray array = obj.optJSONArray("items");
            if (array != null) {
                items = new Audio[array.length()];
                for (int i = 0; i < items.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        items[i] = (Audio) new Audio().deserialize(obj1);
                    }
                }
            }
        }

        return this;
    }
}
