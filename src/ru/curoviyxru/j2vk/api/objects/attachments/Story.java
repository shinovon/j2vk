package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class Story extends Attachment {

    public int id;
    public long owner_id;
    public String type;
    public Video video;
    public Photo photo;
    public static final String VIDEO = "video", PHOTO = "photo";

    public String toString() {
        return "story" + owner_id + "_" + id;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optInt("id");
        owner_id = json.optLong("owner_id");
        type = json.optString("type");

        JSONObject obj = json.optJSONObject("photo");
        if (obj != null) {
            this.photo = (Photo) new Photo().deserialize(obj);
        }
        obj = json.optJSONObject("video");
        if (obj != null) {
            this.video = (Video) new Video().deserialize(obj);
        }

        return this;
    }
}
