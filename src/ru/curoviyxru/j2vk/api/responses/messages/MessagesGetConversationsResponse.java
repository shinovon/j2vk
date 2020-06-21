package ru.curoviyxru.j2vk.api.responses.messages;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.Conversation;
import ru.curoviyxru.j2vk.api.objects.Message;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetConversationsResponse extends VKExtendedResponse {

    public int count;
    public ConversationResponseItem[] items;
    public int unreadCount;

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
            count = json.optInt("count");
            JSONArray array = json.optJSONArray("items");
            if (array != null) {
                items = new ConversationResponseItem[array.length()];
                for (int i = 0; i < items.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        items[i] = (ConversationResponseItem) new ConversationResponseItem().deserialize(obj1);
                    }
                }
            }
            unreadCount = json.optInt("unread_count");
        }

        return this;
    }

    public static class ConversationResponseItem extends VKSerializableObject {

        public Conversation conversation;
        public Message lastMessage;

        public boolean hasLastMessage() {
            return lastMessage != null;
        }

        public boolean hasConversation() {
            return conversation != null;
        }

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            JSONObject obj = json.optJSONObject("conversation");
            if (obj != null) {
                conversation = (Conversation) new Conversation().deserialize(obj);
            }
            obj = json.optJSONObject("last_message");
            if (obj != null) {
                lastMessage = (Message) new Message().deserialize(obj);
            }

            return this;
        }
    }
}
