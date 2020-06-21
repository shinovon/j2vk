package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesPinResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesPin extends VKRequest {
    public MessagesPin(long peer_id, int msg_id) {
        super(MessagesPinResponse.class, "messages.pin");

        setArgument("peer_id", peer_id);
        setArgument("message_id", msg_id);
    }
}
