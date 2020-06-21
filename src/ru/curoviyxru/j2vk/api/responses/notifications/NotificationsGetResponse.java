package ru.curoviyxru.j2vk.api.responses.notifications;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Notification;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class NotificationsGetResponse extends VKExtendedResponse {
    
    public Notification[] items;
    public String nextFrom;
    
    public boolean hasItems() {
        return items != null && items.length > 0;
    }
    
    public VKSerializableObject deserialize(JSONObject json1) {
        super.deserialize(json1);
        if (json1 == null) {
            return this;
        }

        JSONObject json = json1.optJSONObject("response");
        if (json != null) {
            JSONArray array = json.optJSONArray("items");
            if (array != null) {
                items = new Notification[array.length()];
                for (int i = 0; i < items.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        items[i] = (Notification) new Notification().deserialize(obj1);
                    }
                }
            }
            nextFrom = json.optString("next_from");
        }

        return this;
    }
}
