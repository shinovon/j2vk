package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class Graffiti extends Attachment implements ImageAttachment {

    public int width, height, id;
    public long owner_id;
    public String url;

    public boolean hasURL() {
        return !isEmpty(url);
    }

    public String getURL(int nearWidth) {
        return url;
    }
    
    public int getWidth(int nearWidth) {
        return width;
    }
    
    public int getHeight(int nearWidth) {
        return height;
    }
    
    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        url = json.has("src") ? json.optString("src") : json.optString("url");
        width = json.optInt("width");
        height = json.optInt("height");
        owner_id = json.optLong("owner_id");
        id = json.optInt("id");

        return this;
    }

    public String toString() {
        return "graffiti" + owner_id + "_" + id;
    }
}
