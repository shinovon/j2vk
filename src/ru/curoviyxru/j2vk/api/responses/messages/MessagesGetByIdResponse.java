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
public class MessagesGetByIdResponse extends VKExtendedResponse {

    public Message[] messages;
    public int count;

    public final boolean hasMessages() {
        return messages != null && messages.length > 0;
    }

    public final Message getMessage() {
        return hasMessages() ? messages[0] : null;
    }

    public final boolean hasMessage() {
        return getMessage() != null;
    }

    public final VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject obj = json.optJSONObject("response");
        if (obj != null) {
            count = obj.optInt("count");
            JSONArray array = obj.optJSONArray("items");
            if (array != null) {
                messages = new Message[array.length()];
                for (int i = 0; i < messages.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        messages[i] = (Message) new Message().deserialize(obj1);
                    }
                }
            }
        }

        return this;
    }
}
