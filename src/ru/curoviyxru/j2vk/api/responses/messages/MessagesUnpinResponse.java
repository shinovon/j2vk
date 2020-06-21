package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesUnpinResponse extends VKResponse {
    
    public int response;
    
    public boolean isSuccessful() {
        return response == 1;
    }
    
    public final VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }
        
        response = json.optInt("response");
        
        return this;
    }
}
