package ru.curoviyxru.j2vk.api.responses.notifications;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class NotificationsMarkAsViewedResponse extends VKResponse {
    
    boolean response;
    
    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        response = fromInteger(json.optInt("response"));

        return this;
    }
}
