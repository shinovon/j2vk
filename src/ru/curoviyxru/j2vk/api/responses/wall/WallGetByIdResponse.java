package ru.curoviyxru.j2vk.api.responses.wall;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Post;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class WallGetByIdResponse extends VKExtendedResponse {
    
    public Post post;
    
    public boolean hasPost() {
        return post != null;
    }    
    
    public VKSerializableObject deserialize(JSONObject obj) {
        super.deserialize(obj);
        if (obj == null) return this;
        
        JSONObject json = obj.optJSONObject("response");
        if (json != null) {
            JSONArray array = json.optJSONArray("items");
            if (array != null && array.length() > 0) post = (Post) new Post().deserialize(array.optJSONObject(0));
        }
        
        return this;
    }
}
