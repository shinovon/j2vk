package ru.curoviyxru.j2vk.api.responses.docs;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class DocsSaveResponse extends VKResponse {

    public Attachment attachment;

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            attachment = Attachment.parse(response);
        }

        return this;
    }
}
