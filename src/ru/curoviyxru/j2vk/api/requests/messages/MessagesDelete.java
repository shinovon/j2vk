package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesDeleteResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesDelete extends VKRequest {

    public MessagesDelete() {
        super(MessagesDeleteResponse.class, "messages.delete");
    }

    public MessagesDelete(int id) {
        this();
        setMessageId(id);
    }

    public MessagesDelete setMessageId(int id) {
        setArgument("message_ids", id);

        return this;
    }

    public MessagesDelete setDeleteForAll(boolean bool) {
        setArgument("delete_for_all", bool ? "1" : null);

        return this;
    }
}
