package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class Photo extends Attachment implements ImageAttachment {

    public boolean hasSizes() {
        return sizes != null && sizes.hasSizes();
    }

    public Size getSize(int width, boolean dnc) {
        return hasSizes() ? sizes.getSize(width, dnc) : null;
    }

    public boolean hasAccessKey() {
        return !isEmpty(access_key);
    }

    public String toString() {
        return "photo" + owner_id + "_" + id;
    }

    public String getURL(int nearWidth) {
        if (sizes == null) {
            return null;
        }
        Size s = sizes.getSize(nearWidth, false);
        if (s == null) {
            return null;
        }
        return s.url;
    }
    
    public String getURL(String size) {
        if (sizes == null) {
            return null;
        }
        Size s = sizes.getSize(size);
        if (s == null) {
            return null;
        }
        return s.url;
    }

    public int getWidth(int nearWidth) {
        if (sizes == null) {
            return nearWidth;
        }
        Size s = sizes.getSize(nearWidth, false);
        if (s == null) {
            return nearWidth;
        }
        return s.width > 0 ? s.width : nearWidth;
    }

    public int getHeight(int nearWidth) {
        if (sizes == null) {
            return nearWidth;
        }
        Size s = sizes.getSize(nearWidth, false);
        if (s == null) {
            return nearWidth;
        }
        return s.height > 0 ? s.height : nearWidth;
    }

    public static class Sizes extends VKSerializableObject {

        public Size[] sizes;

        public Size getSize(int width, boolean doNotCut) {
            int smallestIndex = -1;
            int smallest = Integer.MAX_VALUE;
            int biggestIndex = -1;
            int biggest = Integer.MIN_VALUE;
            for (int i = 0; i < sizes.length; i++) {
                Size s = sizes[i];
                if (s == null) {
                    continue;
                }
                if (s.width == width) {
                    return s;
                }
                if (s.width > width && s.width < smallest) {
                    if (!doNotCut || !(s.type != null && (s.type.equals("o") || s.type.equals("p") || s.type.equals("q") || s.type.equals("r")))) {
                        smallest = s.width;
                        smallestIndex = i;
                    }
                }
                if (s.width > biggest) {
                    if (!doNotCut || !(s.type != null && (s.type.equals("o") || s.type.equals("p") || s.type.equals("q") || s.type.equals("r")))) {
                        biggest = s.width;
                        biggestIndex = i;
                    }
                }
            }
            return smallestIndex != -1 ? sizes[smallestIndex] : biggestIndex != -1 ? sizes[biggestIndex] : null;
        }
        
        public Size getSize(String size) {
            if (size != null) for (int i = 0; i < sizes.length; i++) {
                Size s = sizes[i];
                if (s == null) {
                    continue;
                }
                if (s.type != null && s.type.equals(size)) {
                    return s;
                }
            }
            return getSize(Integer.MAX_VALUE, true);
        }

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            JSONArray array = json.optJSONArray("sizes");
            if (array != null) {
                sizes = new Size[array.length()];
                for (int i = 0; i < sizes.length; i++) {
                    JSONObject obj = array.optJSONObject(i);
                    if (obj != null) {
                        sizes[i] = (Size) new Size().deserialize(obj);
                    }
                }
            }

            return this;
        }

        public boolean hasSizes() {
            return sizes != null && sizes.length > 0;
        }
    }
    public int id, album_id, date;
    public long owner_id, user_id;
    public String text, access_key;
    public Sizes sizes;

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optInt("id");
        album_id = json.optInt("album_id");
        owner_id = json.optLong("owner_id");
        user_id = json.optLong("user_id");
        date = json.optInt("date");
        text = json.optString("text");
        access_key = json.optString("access_key");
        sizes = (Sizes) new Sizes().deserialize(json);

        return this;
    }

    public static class Size extends VKSerializableObject {

        public String type, url;
        public int width, height;

        public boolean hasType() {
            return !isEmpty(type);
        }

        public static int getMaxSize(char t) {
            switch (t) {
                case 's':
                    return 75;
                case 'm':
                case 'o':
                    return 130;
                case 'x':
                    return 604;
                case 'p':
                    return 200;
                case 'q':
                    return 320;
                case 'r':
                    return 510;
                case 'y':
                    return 807;
                case 'z':
                    return 1080;
                case 'w':
                    return 2560;
                default:
                    return 0;
            }
        }

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            type = json.optString("type");
            url = json.optString("url");
            width = json.optInt("width");
            height = json.optInt("height");

            if (hasType() && (width == 0 || height == 0)) {
                int calc = getMaxSize(type.charAt(0));
                width = Math.max(width, calc);
                height = Math.max(height, calc);
            }

            return this;
        }
    }
}
