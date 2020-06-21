package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class Link extends Attachment {
    
    public String url, title, caption, description;
    public Photo photo;

    public String toString() {
        return "link";
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        url = json.optString("url");
        title = json.optString("title");
        caption = json.optString("caption");
        description = json.optString("description");

        JSONObject obj = json.optJSONObject("photo");
        if (obj != null) {
            this.photo = (Photo) new Photo().deserialize(obj);
        }

        return this;
    }
}
