package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.Conversation;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetConversationsByIdResponse extends VKExtendedResponse {

    public Conversation[] conversations;
    public int count;

    public final boolean hasConversations() {
        return conversations != null && conversations.length > 0;
    }

    public final Conversation getConversation() {
        return hasConversations() ? conversations[0] : null;
    }

    public final boolean hasConversation() {
        return getConversation() != null;
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
                conversations = new Conversation[array.length()];
                for (int i = 0; i < conversations.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        conversations[i] = (Conversation) new Conversation().deserialize(obj1);
                    }
                }
            }
        }

        return this;
    }
}
