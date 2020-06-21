package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesGetConversationsResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetConversations extends VKRequest {

    public static final String ALL = "all";
    public static final String UNREAD = "unread";
    public static final String IMPORTANT = "important";
    public static final String UNANSWERED = "unanswered";

    public MessagesGetConversations() {
        super(MessagesGetConversationsResponse.class, "messages.getConversations");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
    }

    public final MessagesGetConversations setCount(int i) {
        setArgument("count", i);

        return this;
    }

    public final MessagesGetConversations setGroupId(long i) {
        setArgument("group_id", i);

        return this;
    }

    public final MessagesGetConversations setOffset(int i) {
        setArgument("offset", i);

        return this;
    }

    public final MessagesGetConversations setStartMessageId(int i) {
        setArgument("start_message_id", i);

        return this;
    }

    public final MessagesGetConversations setFilter(String i) {
        setArgument("filter", i);

        return this;
    }
}
