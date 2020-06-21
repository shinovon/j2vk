package ru.curoviyxru.j2vk.api.responses.audio;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.AudioPlaylist;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class AudioGetPlaylistsResponse extends VKExtendedResponse {

    public AudioPlaylist[] items;

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
            JSONArray arr = response.optJSONArray("items");
            if (arr != null) {
                items = new AudioPlaylist[arr.length()];
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject item = arr.optJSONObject(i);
                    if (item != null) {
                        items[i] = (AudioPlaylist) new AudioPlaylist().deserialize(item);
                    }
                }
            }
        }

        return this;
    }
}
