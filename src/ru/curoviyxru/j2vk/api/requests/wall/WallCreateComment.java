package ru.curoviyxru.j2vk.api.requests.wall;

import java.util.Random;
import java.util.Vector;
import ru.curoviyxru.j2vk.TextUtil;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.HTTPClient;
import ru.curoviyxru.j2vk.api.objects.attachments.Comment;
import ru.curoviyxru.j2vk.api.objects.attachments.Post;
import ru.curoviyxru.j2vk.api.responses.wall.WallCreateCommentResponse;

/**
 *
 * @author curoviyxru
 */
public class WallCreateComment extends VKRequest {

    static Random random = new Random();

    public WallCreateComment() {
        super(WallCreateCommentResponse.class, "wall.createComment");
        setGuid(random.nextLong());
    }

    public WallCreateComment setGuid(long id) {
        setArgument("guid", String.valueOf(id > 0 ? id : id * -1));

        return this;
    }

    public WallCreateComment(Post p, String msg) {
        this();

        setPost(p);
        setMessage(msg);
    }
    
    public WallCreateComment(long oid, int id, String msg) {
        this();

        setPost(oid, id);
        setMessage(msg);
    }

    public WallCreateComment setAttachments(Vector v) {
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

    public WallCreateComment setAttachment(String s) {
        setArgument("attachments", s);

        return this;
    }

    public WallCreateComment(Post p, String msg, int reply_to) {
        this(p, msg);

        setReplyTo(reply_to);
    }

    public WallCreateComment setPost(Post p) {
        setPost(p.owner_id, p.post_id);
        
        return this;
    }
    
    public WallCreateComment setPost(long owner_id, int post_id) {
        setArgument("owner_id", String.valueOf(owner_id));
        setArgument("post_id", String.valueOf(post_id));

        return this;
    }

    public WallCreateComment setReplyTo(int reply_to) {
        setArgument("reply_to_comment", reply_to);

        return this;
    }

    public WallCreateComment setMessage(String msg) {
        setArgument("message", msg != null ? HTTPClient.urlEncode(msg) : null);

        return this;
    }

    public WallCreateComment setReply(int replyId) {
        setArgument("reply_to_comment", replyId);
        return this;
    }
}
