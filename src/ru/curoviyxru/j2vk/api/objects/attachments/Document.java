package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Photo.Sizes;

/**
 *
 * @author curoviyxru
 */
public class Document extends Attachment {

    public int id, size, date, type;
    public long owner_id;
    public String title, ext, url;
    public static final int TEXT = 1, ARCHIVE = 2, GIF = 3, IMAGE = 4, AUDIO = 5, VIDEO = 6, EBOOK = 7, UNKNOWN = 8;
    public DocumentPreview preview;

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optInt("id");
        owner_id = json.optLong("owner_id");
        size = json.optInt("size");
        date = json.optInt("date");
        type = json.optInt("type");
        title = json.optString("title");
        ext = json.optString("ext");
        url = json.optString("url");
        JSONObject obj = json.optJSONObject("preview");
        if (obj != null) {
            preview = (DocumentPreview) new DocumentPreview().deserialize(obj);
        }

        return this;
    }

    public static String getSizeString(int size) {
        if (size >= 0x40000000) {
            return ((float) ((int) (((float) size) / 0x40000000 * 10))) / 10 + " GiB";
        }
        if (size >= 0x100000) {
            return ((float) ((int) (((float) size) / 0x100000 * 10))) / 10 + " MiB";
        }
        if (size >= 0x400) {
            return ((float) ((int) (((float) size) / 0x400 * 10))) / 10 + " KiB";
        }
        return size + " B";
    }

    public static class DocumentPreview extends VKSerializableObject {

        public Sizes sizes;
        public Graffiti graffiti;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            JSONObject obj = json.optJSONObject("photo");
            if (obj != null) {
                sizes = (Sizes) new Sizes().deserialize(obj);
            }
            obj = json.optJSONObject("graffiti");
            if (obj != null) {
                graffiti = (Graffiti) new Graffiti().deserialize(obj);
            }

            return this;
        }
    }

    public String bottom() {
        return title + "\n" + getSizeString(size) + " - " + ext.toUpperCase();
    }

    public String toString() {
        return "doc" + owner_id + "_" + id;
    }
}
