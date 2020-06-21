package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesGetHistoryResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetHistory extends VKRequest {

    public MessagesGetHistory(long peer_id, int count, int offset) {
        super(MessagesGetHistoryResponse.class, "messages.getHistory");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
        setPeerId(peer_id);
        setCount(count);
        setOffset(offset);
    }

    public MessagesGetHistory(long peer_id, int count) {
        this(peer_id, count, 0);
    }

    public MessagesGetHistory setPeerId(long peer_id) {
        setArgument("peer_id", peer_id);

        return this;
    }

    public MessagesGetHistory setCount(int count) {
        setArgument("count", count);

        return this;
    }

    public MessagesGetHistory setOffset(int off) {
        setArgument("offset", off);

        return this;
    }

    public MessagesGetHistory setReverse(boolean b) {
        setArgument("rev", fromBoolean(b));

        return this;
    }

    public MessagesGetHistory setStartMessageId(int msgId) {
        setArgument("start_message_id", msgId);

        return this;
    }
}
