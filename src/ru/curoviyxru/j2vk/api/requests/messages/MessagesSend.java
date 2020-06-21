package ru.curoviyxru.j2vk.api.requests.messages;

import java.util.Random;
import java.util.Vector;
import ru.curoviyxru.j2vk.TextUtil;
import ru.curoviyxru.j2vk.api.objects.Message;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesSendResponse;
import ru.curoviyxru.j2vk.HTTPClient;

/**
 *
 * @author curoviyxru
 */
public class MessagesSend extends VKRequest {

    static Random random = new Random();

    public MessagesSend() {
        super(MessagesSendResponse.class, "messages.send");
        setRandomId(random.nextLong());
    }

    public MessagesSend setRandomId(long id) {
        setArgument("random_id", String.valueOf(id > 0 ? id : id * -1));

        return this;
    }

    public MessagesSend(long peer_id, String msg) {
        this();

        setPeerId(peer_id);
        setMessage(msg);
    }

    public MessagesSend setAttachments(Vector v) {
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

    public MessagesSend setAttachment(String s) {
        setArgument("attachment", s);

        return this;
    }

    public MessagesSend(long peer_id, String msg, int reply_to) {
        this(peer_id, msg);

        setReplyTo(reply_to);
    }

    public MessagesSend setForwardMessage(int msg_id) {
        setArgument("forward_messages", msg_id);

        return this;
    }

    public MessagesSend setPeerId(long peer_id) {
        setArgument("peer_id", peer_id);

        return this;
    }

    public MessagesSend setReplyTo(int reply_to) {
        setArgument("reply_to", reply_to);

        return this;
    }

    public MessagesSend setMessage(String msg) {
        setArgument("message", msg != null ? HTTPClient.urlEncode(msg) : null);

        return this;
    }

    public MessagesSend setForwardedMessages(Vector v) {
        if (v == null) {
            setArgument("forward_messages", null);
        }

        Vector newV = new Vector();

        for (int i = 0; i < v.size(); i++) {
            if (v.elementAt(i) != null) {
                newV.addElement(String.valueOf(((Message) v.elementAt(i)).id));
            }
        }

        if (v.isEmpty()) {
            setArgument("forward_messages", null);
        } else {
            setArgument("forward_messages", TextUtil.join(",", newV));
        }
        return this;
    }

    public MessagesSend setReply(int replyId) {
        setArgument("reply_to", replyId);
        return this;
    }
}
