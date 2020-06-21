package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.Message;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetHistoryResponse extends VKExtendedResponse {

    public Message[] items;
    public int count;

    public VKSerializableObject deserialize(JSONObject json1) {
        super.deserialize(json1);
        if (json1 == null) {
            return this;
        }

        JSONObject json = json1.optJSONObject("response");
        if (json != null) {
            count = json.optInt("count");
            JSONArray array = json.optJSONArray("items");
            if (array != null) {
                items = new Message[array.length()];
                for (int i = 0; i < items.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        items[i] = (Message) new Message().deserialize(obj1);
                    }
                }
            }
        }

        return this;
    }

    public boolean hasItems() {
        return items != null && items.length > 0;
    }
}
