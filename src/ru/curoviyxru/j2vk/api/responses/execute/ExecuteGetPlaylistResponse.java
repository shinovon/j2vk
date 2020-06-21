package ru.curoviyxru.j2vk.api.responses.execute;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Audio;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class ExecuteGetPlaylistResponse extends VKResponse {

    //int id; 
    //long owner_id;
    //String access_key;
    public Audio[] items;

    public boolean hasItems() {
        return items != null && items.length > 0;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            JSONArray arr = response.optJSONArray("audios");
            if (arr != null) {
                items = new Audio[arr.length()];
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject item = arr.optJSONObject(i);
                    if (item != null) {
                        items[i] = (Audio) new Audio().deserialize(item);
                    }
                }
            }
        }

        return this;
    }
}
