package ru.curoviyxru.j2vk.api.requests.messages;

import java.util.Vector;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesGetByIdResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetById extends VKRequest {

    Vector ids = new Vector();

    public MessagesGetById() {
        super(MessagesGetByIdResponse.class, "messages.getById");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
    }

    public MessagesGetById(int id) {
        this();
        addMessageId(id);
    }

    public MessagesGetById addMessageId(int id) {
        if (id < 0) {
            id = id * -1;
        }
        if (ids.indexOf(new Integer(id)) != -1) {
            return this;
        }

        ids.addElement(new Integer(id));
        updateIds();

        return this;
    }

    public MessagesGetById removeMessageId(int id) {
        if (id < 0) {
            id = id * -1;
        }
        if (ids.indexOf(new Integer(id)) == -1) {
            return this;
        }

        ids.removeElement(new Integer(id));
        updateIds();

        return this;
    }

    private void updateIds() {
        if (hasMessageIds()) {
            setArgument("message_ids", join(",", ids));
        } else {
            setArgument("message_ids", null);
        }
    }

    public boolean hasMessageIds() {
        return ids != null && ids.size() > 0;
    }

    public Vector getMessageIds() {
        return ids;
    }
}
