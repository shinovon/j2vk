package ru.curoviyxru.j2vk.api.requests.wall;

import java.util.Vector;
import ru.curoviyxru.j2vk.TextUtil;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.HTTPClient;
import ru.curoviyxru.j2vk.api.responses.wall.WallEditCommentResponse;

/**
 *
 * @author curoviyxru
 */
public class WallEditComment extends VKRequest {

    public WallEditComment() {
        super(WallEditCommentResponse.class, "wall.editComment");
    }

    public WallEditComment(long peer_id, int msg_id, String text) {
        this();

        setOwnerId(peer_id);
        setCommentId(msg_id);
        setMessage(text);
    }

    public WallEditComment setOwnerId(long peer_id) {
        setArgument("owner_id", peer_id);

        return this;
    }

    public WallEditComment setCommentId(int com_id) {
        setArgument("comment_id", com_id);

        return this;
    }

    public WallEditComment setMessage(String msg) {
        setArgument("message", !isEmpty(msg) ? HTTPClient.urlEncode(msg) : null);

        return this;
    }

    public WallEditComment setAttachments(Vector v) {
        if (v == null) {
            setArgument("attachments", null);
        }

        Vector newV = new Vector();

        for (int i = 0; i < v.size(); i++) {
            if (v.elementAt(i) != null) {
                newV.addElement(v.elementAt(i).toString());
            }
        }

        if (v.isEmpty()) {
            setArgument("attachments", null);
        } else {
            setArgument("attachments", TextUtil.join(",", newV));
        }

        return this;
    }

    public WallEditComment setAttachment(String s) {
        setArgument("attachments", s);

        return this;
    }
}
