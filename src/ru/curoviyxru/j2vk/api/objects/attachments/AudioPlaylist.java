package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Audio.Artist;
import ru.curoviyxru.j2vk.api.objects.attachments.Audio.Genre;
import ru.curoviyxru.j2vk.api.objects.attachments.Audio.Thumbs;

/**
 *
 * @author curoviyxru
 */
public class AudioPlaylist extends Attachment {

    public int id, type;
    public long owner_id;
    public String title, description;
    public Genre[] genres;
    //int count;
    //boolean is_following;
    //int followers;
    //int plays;
    //int create_time;
    //int update_time;
    //Original original; -> int owner_id, playlist_id; String access_key;
    public Thumbs photo;
    public Artist[] main_artists;
    public String access_key;
    //boolean is_explicit;
    //String album_type;

    public String toString() {
        return "audio_playlist" + owner_id + "_" + id;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optInt("id");
        owner_id = json.optLong("owner_id");
        type = json.optInt("type");
        title = json.optString("title");
        access_key = json.optString("access_key");
        JSONObject obj = json.optJSONObject("photo");
        if (obj != null) {
            photo = (Thumbs) new Thumbs().deserialize(obj);
        }
        JSONArray arr = json.optJSONArray("main_artists");
        if (arr != null) {
            main_artists = new Artist[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                JSONObject item = arr.optJSONObject(i);
                if (item != null) {
                    main_artists[i] = (Artist) new Artist().deserialize(item);
                }
            }
        }
        arr = json.optJSONArray("genres");
        if (arr != null) {
            genres = new Genre[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                JSONObject item = arr.optJSONObject(i);
                if (item != null) {
                    genres[i] = (Genre) new Genre().deserialize(item);
                }
            }
        }

        return this;
    }

    public String bottom() {
        if (!hasArtists()) {
            return description;
        }

        StringBuffer b = new StringBuffer();
        for (int i = 0; i < main_artists.length; i++) {
            Artist a = main_artists[i];
            if (a == null || a.name == null) {
                continue;
            }
            b.append(a.name);
            if (i != main_artists.length - 1) {
                b.append(", ");
            }
        }

        return b.toString();
    }

    public boolean hasArtists() {
        return main_artists != null && main_artists.length > 0;
    }
}
