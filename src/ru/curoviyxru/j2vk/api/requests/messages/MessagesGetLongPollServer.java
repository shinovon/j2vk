package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesGetLongPollServerResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetLongPollServer extends VKRequest {

    //lp_version, group_id, need_pts
    public MessagesGetLongPollServer() {
        super(MessagesGetLongPollServerResponse.class, "messages.getLongPollServer");
        setArgument("lp_version", 3);
    }
}
