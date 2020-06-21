package ru.curoviyxru.j2vk.api.responses.photos;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class PhotosGetMessagesUploadServerResponse extends VKResponse {

    public String url;

    public boolean hasURL() {
        return !isEmpty(url);
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject obj = json.optJSONObject("response");
        if (obj != null) {
            url = obj.optString("upload_url");
        }

        return this;
    }
}
