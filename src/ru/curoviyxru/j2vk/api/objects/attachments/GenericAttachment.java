package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class GenericAttachment extends Attachment {

    public int id;
    public long owner_id;
    public String type;
    public JSONObject json;

    public GenericAttachment(String type) {
        this.type = type;
    }

    public String toString() {
        return type + owner_id + "_" + id;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        this.json = json;
        id = json.optInt("id");
        owner_id = json.optLong("owner_id");

        return this;
    }
}
