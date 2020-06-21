package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class Gift extends Attachment implements ImageAttachment {
    public int id;
    public String thumb_48, thumb_96, thumb_256;

    public String getURL(int nearWidth) {
        if (nearWidth <= 48) {
            return thumb_48;
        }
        if (nearWidth <= 96) {
            return thumb_96;
        }
        return thumb_256;
    }
    
    public int getWidth(int nearWidth) {
        if (nearWidth <= 48) {
            return 48;
        }
        if (nearWidth <= 96) {
            return 96;
        }
        return 256;
    }
    
    public int getHeight(int nearWidth) {
        if (nearWidth <= 48) {
            return 48;
        }
        if (nearWidth <= 96) {
            return 96;
        }
        return 256;
    }
    
    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        thumb_48 = json.optString("thumb_48");
        thumb_96 = json.optString("thumb_96");
        thumb_256 = json.optString("thumb_256");
        id = json.optInt("id");

        return this;
    }

    public String toString() {
        return "gift" + id;
    }
}
