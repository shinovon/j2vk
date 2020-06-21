package ru.curoviyxru.j2vk.api.responses.docs;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class DocsGetMessagesUploadServerResponse extends VKResponse {

    public String upload_url;

    public boolean hasURL() {
        return !isEmpty(upload_url);
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) return this;

        JSONObject response = json.optJSONObject("response");
        if (response != null) upload_url = response.optString("upload_url");

        return this;
    }
}
