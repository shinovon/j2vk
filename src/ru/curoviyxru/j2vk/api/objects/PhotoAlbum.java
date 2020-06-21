package ru.curoviyxru.j2vk.api.objects;

import org.json.me.JSONObject;

/**
 *
 * @author curoviyxru
 */
public class PhotoAlbum extends Attachment {
    
    public int id;
    public int thumb_id;
    public long owner_id;
    public String title;
    public String description;
    public int created;
    public int updated;
    public int size;
    public String thumb_src;
    
    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optInt("id");
        thumb_id = json.optInt("thumb_id");
        owner_id = json.optLong("owner_id");
        title = json.optString("title");
        description = json.optString("description");
        created = json.optInt("created");
        updated = json.optInt("updated");
        size = json.optInt("size");
        thumb_src = json.optString("thumb_src");
        
        return this;
    }

    public String toString() {
        return "album"+owner_id+"_"+id;
    }
}
