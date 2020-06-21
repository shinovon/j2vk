package ru.curoviyxru.j2vk.api.responses.photos;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Photo;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class PhotosSaveMessagesPhotoResponse extends VKResponse {

    public Photo photo;

    public boolean hasPhoto() {
        return photo != null;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONArray arr = json.optJSONArray("response");
        if (arr != null && arr.length() > 0) {
            JSONObject photoobj = arr.optJSONObject(0);
            if (photoobj != null) {
                photo = (Photo) new Photo().deserialize(photoobj);
            }
        }

        return this;
    }
}
