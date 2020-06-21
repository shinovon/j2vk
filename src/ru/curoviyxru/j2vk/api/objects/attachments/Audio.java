package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

public class Audio extends Attachment {

    public String getId() {
        return owner_id + "_" + id;
    }
    public int duration;
    //int date;
    public String url;
    //int lyrics_id;
    //int genre_id;
    //boolean is_licensed;
    //boolean is_hq;
    //boolean is_explicit;
    //Artist[] main_artists;
    Album album;
    public int id;
    public long owner_id;
    public String artist;
    public String title;

    public String toString() {
        return "audio" + owner_id + "_" + id;
    }

    public String getName() {
        return artist + " - " + title;
    }

    public boolean hasPhoto_68() {
        return hasAlbum() && album.hasThumbs() && album.thumb.hasPhoto_68();
    }

    public String getPhoto_68() {
        return hasPhoto_68() ? album.thumb.photo_68 : null;
    }

    public boolean hasAlbum() {
        return album != null;
    }

    public String getPhoto() {
        return getPhoto_600();
    }

    public boolean hasPhoto_135() {
        return hasAlbum() && album.hasThumbs() && album.thumb.hasPhoto_135();
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        duration = json.optInt("duration");
        url = json.optString("url");
        id = json.optInt("id");
        owner_id = json.optLong("owner_id");
        artist = json.optString("artist");
        title = json.optString("title");
        JSONObject obj = json.optJSONObject("album");
        if (obj != null) {
            album = (Album) new Album().deserialize(obj);
        }

        return this;
    }

    private boolean hasPhoto_600() {
        return hasAlbum() && album.hasThumbs() && album.thumb.hasPhoto_600();
    }

    private boolean hasPhoto_300() {
        return hasAlbum() && album.hasThumbs() && album.thumb.hasPhoto_300();
    }

    public boolean hasPhoto() {
        return !isEmpty(getPhoto());
    }

    private boolean hasPhoto_270() {
        return hasAlbum() && album.hasThumbs() && album.thumb.hasPhoto_270();
    }

    public String getPhoto_600() {
        return hasPhoto_600() ? album.thumb.photo_600 : getPhoto_300();
    }

    public String getPhoto_300() {
        return hasPhoto_300() ? album.thumb.photo_300 : getPhoto_270();
    }

    public String getPhoto_270() {
        return hasPhoto_270() ? album.thumb.photo_270 : getPhoto_135();
    }

    public String getPhoto_135() {
        return hasPhoto_135() ? album.thumb.photo_135 : getPhoto_68();
    }

    public static class Thumbs extends VKSerializableObject {

        public int width, height;
        public String photo_68, photo_135, photo_270, photo_300, photo_600;

        public boolean hasPhoto_68() {
            return !isEmpty(photo_68);
        }

        public boolean hasPhoto_600() {
            return !isEmpty(photo_600);
        }

        public boolean hasPhoto_135() {
            return !isEmpty(photo_135);
        }

        public boolean hasPhoto_300() {
            return !isEmpty(photo_300);
        }

        public boolean hasPhoto_270() {
            return !isEmpty(photo_270);
        }

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            width = json.optInt("width");
            height = json.optInt("height");
            photo_68 = json.optString("photo_68");
            photo_135 = json.optString("photo_135");
            photo_270 = json.optString("photo_270");
            photo_300 = json.optString("photo_300");
            photo_600 = json.optString("photo_600");

            return this;
        }
    }

    public static class Album extends VKSerializableObject {

        public int id, owner_id;
        public String title;
        public Thumbs thumb;

        public boolean hasThumbs() {
            return thumb != null;
        }

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            id = json.optInt("id");
            owner_id = json.optInt("owner_id");
            title = json.optString("title");
            JSONObject obj = json.optJSONObject("thumb");
            if (obj != null) {
                thumb = (Thumbs) new Thumbs().deserialize(obj);
            }

            return this;
        }
    }

    public static class Artist extends VKSerializableObject {

        public String name, id, domain;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            name = json.optString("name");
            id = json.optString("id");
            domain = json.optString("domain");

            return this;
        }
    }

    public static class Genre extends VKSerializableObject {

        public int id;
        public String name;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            id = json.optInt("id");
            name = json.optString("name");

            return this;
        }
    }
}
