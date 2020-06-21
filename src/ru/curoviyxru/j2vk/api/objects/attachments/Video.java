package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class Video extends Attachment {

    public int id, duration;
    public long owner_id;
    public String title, description, platform, player;
    public String mp4_1080, mp4_720, mp4_480, mp4_360, mp4_240, mp4_144;

    public boolean hasURL() {
        return !isEmpty(getURL());
    }

    public String getURL() {
        if (!isEmpty(mp4_1080)) {
            return mp4_1080;
        }
        if (!isEmpty(mp4_720)) {
            return mp4_720;
        }
        if (!isEmpty(mp4_480)) {
            return mp4_480;
        }
        if (!isEmpty(mp4_360)) {
            return mp4_360;
        }
        if (!isEmpty(mp4_240)) {
            return mp4_240;
        }
        if (!isEmpty(mp4_144)) {
            return mp4_144;
        }
        return player;
    }
    
    public boolean isExternal() {
        return !isEmpty(platform);
    }

    public String toString() {
        return "video" + owner_id + "_" + id;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optInt("id");
        owner_id = json.optLong("owner_id");
        JSONObject obj = json.optJSONObject("files");
        if (obj != null) {
            mp4_1080 = obj.optString("mp4_1080");
            mp4_720 = obj.optString("mp4_720");
            mp4_480 = obj.optString("mp4_480");
            mp4_360 = obj.optString("mp4_360");
            mp4_240 = obj.optString("mp4_240");
            mp4_144 = obj.optString("mp4_144");
        }
        description = json.optString("description");
        title = json.optString("title");
        platform = json.optString("platform");
        player = json.optString("player");
        duration = json.optInt("duration");

        return this;
    }
}
