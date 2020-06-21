package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Message;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesPinResponse extends VKResponse {
    
    public Message msg;
    
    public boolean hasMessage() {
        return msg != null;
    }
    
    public final VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }
        
        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            msg = (Message) new Message().deserialize(response);
        }
        
        return this;
    }
}
