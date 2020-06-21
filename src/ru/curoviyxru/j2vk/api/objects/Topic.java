package ru.curoviyxru.j2vk.api.objects;

import org.json.me.JSONObject;

/**
 *
 * @author curoviyxru
 */
public class Topic extends VKSerializableObject {

    int id;
    String title;
    int created;
    long created_by;
    int updated;
    long updated_by;
    boolean is_closed, is_fixed; //bool int
    int comments;
    String first_comment;
    String last_comment;
    
    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) return this;
        
        id = json.optInt("id");
        title = json.optString("title");
        created = json.optInt("created");
        created_by = json.optLong("created_by");
        updated = json.optInt("updated");
        updated_by = json.optLong("updated_by");
        is_closed = fromInteger(json.optInt("is_closed"));
        is_fixed = fromInteger(json.optInt("is_fixed"));
        comments = json.optInt("comments");
        first_comment = json.optString("first_comment");
        last_comment = json.optString("last_comment");
        
        return this;
    }
    
}
