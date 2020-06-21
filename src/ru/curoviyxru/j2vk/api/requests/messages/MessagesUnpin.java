package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesUnpinResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesUnpin extends VKRequest {
    public MessagesUnpin(long peer_id) {
        super(MessagesUnpinResponse.class, "messages.unpin");

        setArgument("peer_id", peer_id);
    }
}
