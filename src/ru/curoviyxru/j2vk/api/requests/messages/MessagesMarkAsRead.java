package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesMarkAsReadResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesMarkAsRead extends VKRequest {

    public MessagesMarkAsRead() {
        super(MessagesMarkAsReadResponse.class, "messages.markAsRead");
    }

    public MessagesMarkAsRead(long peer_id, int last_msg) {
        this();

        setPeerId(peer_id);
        setStartMessageId(last_msg);
    }

    private MessagesMarkAsRead setPeerId(long peer_id) {
        setArgument("peer_id", peer_id);

        return this;
    }

    private MessagesMarkAsRead setStartMessageId(int last_msg) {
        setArgument("start_message_id", last_msg);

        return this;
    }
}
