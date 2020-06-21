package ru.curoviyxru.j2vk.api.requests.messages;

import java.util.Vector;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesGetConversationsByIdResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetConversationsById extends VKRequest {

    Vector ids = new Vector();

    public MessagesGetConversationsById() {
        super(MessagesGetConversationsByIdResponse.class, "messages.getConversationsById");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
    }

    public MessagesGetConversationsById(long id) {
        this();
        addPeerId(id);
    }

    public MessagesGetConversationsById addPeerId(long id) {
        if (ids.indexOf(new Long(id)) != -1) {
            return this;
        }

        ids.addElement(new Long(id));
        updateIds();

        return this;
    }

    public MessagesGetConversationsById removePeerId(long id) {
        if (ids.indexOf(new Long(id)) == -1) {
            return this;
        }

        ids.removeElement(new Long(id));
        updateIds();

        return this;
    }

    private void updateIds() {
        if (hasPeerIds()) {
            setArgument("peer_ids", join(",", ids));
        } else {
            setArgument("peer_ids", null);
        }
    }

    public boolean hasPeerIds() {
        return ids != null && ids.size() > 0;
    }

    public Vector getPeerIds() {
        return ids;
    }
}
