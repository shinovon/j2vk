package ru.curoviyxru.j2vk.api.requests.messages;

import java.util.Vector;
import ru.curoviyxru.j2vk.TextUtil;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesEditResponse;
import ru.curoviyxru.j2vk.HTTPClient;

/**
 *
 * @author curoviyxru
 */
public class MessagesEdit extends VKRequest {

    //TODO: addattachs, removeattachs, setlat, setlong
    public MessagesEdit() {
        super(MessagesEditResponse.class, "messages.edit");

        setArgument("keep_forward_messages", "1");
        setArgument("keep_snippets", "1");
    }

    public MessagesEdit(long peer_id, int msg_id, String text) {
        this();

        setPeerId(peer_id);
        setMessageId(msg_id);
        setMessage(text);
    }

    public MessagesEdit setPeerId(long peer_id) {
        setArgument("peer_id", peer_id);

        return this;
    }

    public MessagesEdit setMessageId(int msg_id) {
        setArgument("message_id", msg_id);

        return this;
    }

    public MessagesEdit setMessage(String msg) {
        setArgument("message", !isEmpty(msg) ? HTTPClient.urlEncode(msg) : null);

        return this;
    }

    public MessagesEdit setAttachments(Vector v) {
        if (v == null) {
            setArgument("attachment", null);
        }

        Vector newV = new Vector();

        for (int i = 0; i < v.size(); i++) {
            if (v.elementAt(i) != null) {
                newV.addElement(v.elementAt(i).toString());
            }
        }

        if (v.isEmpty()) {
            setArgument("attachment", null);
        } else {
            setArgument("attachment", TextUtil.join(",", newV));
        }

        return this;
    }

    public MessagesEdit setAttachment(String s) {
        setArgument("attachment", s);

        return this;
    }
}
