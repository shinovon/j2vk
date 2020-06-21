package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class Sticker extends Attachment implements ImageAttachment {

    //images_with_background -> StickerImage[], is_allowed -> boolean, animation_url -> string
    public int product_id, sticker_id;
    public StickerImage[] images;

    public String getURL(int nearWidth) {
        if (images == null) {
            return null;
        }
        StickerImage s = getSize(nearWidth);
        if (s == null) {
            return null;
        }
        return s.url;
    }

    public int getHeight(int nearWidth) {
        if (images == null) {
            return nearWidth;
        }
        StickerImage s = getSize(nearWidth);
        if (s == null) {
            return nearWidth;
        }
        return s.height > 0 ? s.height : nearWidth;
    }

    public int getWidth(int nearWidth) {
        if (images == null) {
            return nearWidth;
        }
        StickerImage s = getSize(nearWidth);
        if (s == null) {
            return nearWidth;
        }
        return s.width > 0 ? s.width : nearWidth;
    }

    public StickerImage getSize(int width) {
        int smallestIndex = -1;
        int smallest = Integer.MAX_VALUE;
        int biggestIndex = -1;
        int biggest = Integer.MIN_VALUE;
        for (int i = 0; i < images.length; i++) {
            StickerImage s = images[i];
            if (s == null) {
                continue;
            }
            if (s.width == width) {
                return s;
            }
            if (s.width > width && s.width < smallest) {
                smallest = s.width;
                smallestIndex = i;
            }
            if (s.width > biggest) {
                biggest = s.width;
                biggestIndex = i;
            }
        }
        return smallestIndex != -1 ? images[smallestIndex] : biggestIndex != -1 ? images[biggestIndex] : null;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        product_id = json.optInt("product_id");
        sticker_id = json.optInt("sticker_id");
        JSONArray array = json.optJSONArray("images");
        if (array != null) {
            images = new StickerImage[array.length()];
            for (int i = 0; i < images.length; i++) {
                JSONObject obj = array.optJSONObject(i);
                if (obj != null) {
                    images[i] = (StickerImage) new StickerImage().deserialize(obj);
                }
            }
        }

        return this;
    }

    public boolean hasImages() {
        return images != null && images.length > 0;
    }

    public String toString() {
        return sticker_id + "";
    }

    public static class StickerImage extends VKSerializableObject {

        public String url;
        public int width, height;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            url = json.optString("url");
            width = json.optInt("width");
            height = json.optInt("height");

            return this;
        }
    }
}
