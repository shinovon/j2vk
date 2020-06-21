package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.PageStorage;
import ru.curoviyxru.j2vk.TextUtil;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.ImItem;
import ru.curoviyxru.j2vk.api.objects.Likeable;
import ru.curoviyxru.j2vk.api.objects.Message;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.user.Page;

/**
 *
 * @author curoviyxru
 */
public class Comment extends Attachment implements Likeable, ImItem {

    public int id;
    public long fromId, ownerId;
    public int date, replyToUser, replyToComment;
    public String text;
    int[] parents_stack;
    public Attachment[] attachments;
    public Thread thread;
    public Post.Likes likes;
    public int postId;
    public boolean out;
    public Comment replyMessage;
    public boolean deleted;
    
    public static String COMMENT_DELETED = "Комментарий удалён пользователем или руководителем страницы";
    
    public boolean isDeleted() {
        return deleted || fromId == 0;
    }
    
    public boolean hasThread() {
        return thread != null;
    }

    public String toString(boolean showName, boolean isPMs, boolean shortName, boolean mentionReply, boolean mentionFwd) {
        if (isDeleted()) return COMMENT_DELETED;
        
        String str2 = "";

        if (showName) {
            Page p = PageStorage.get(fromId);
            if (!isPMs) {
                str2 += out ? Message.LOCALE_YOU : p != null ? shortName ? p.getMessageTitle() : p.getName() : "";
            } else {
                str2 += out ? Message.LOCALE_YOU : "";
            }
            if (!isPMs || out) {
                str2 += ": ";
            }
        }

        if (mentionReply && hasReplyMessage()) {
            Page u = PageStorage.get(replyMessage.fromId);
            if (u != null) {
                str2 += Message.LOCALE_REPLY + "\n";
            }
        }
        if (hasAttachments()) {
            if (this.attachments.length > 1) {
                str2 += Message.LOCALE_ATTACHS + "\n";
            } else {
                str2 += Message.LOCALE_ATTACH + "\n";
            }
        }

        if (!TextUtil.isNullOrEmpty(text)) {
            str2 += text.trim();
        }

        return str2;
    }

    public String toString() {
        return "wall_reply"+ownerId+"_"+id;
    }

    public boolean hasReplyMessage() {
        return replyMessage != null;
    }

    public boolean hasAttachments() {
        return attachments != null && attachments.length > 0;
    }

    public boolean hasReplyUser() {
        return replyToUser != 0;
    }

    public boolean hasText() {
        return !isEmpty(text);
    }

    public boolean canEdDel() {
        return true;
    }

    public int getLastTime() {
        return date;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        deleted = json.optBoolean("deleted");
        id = json.optInt("id");
        fromId = json.optLong("from_id");
        out = fromId == account.id;
        date = json.optInt("date");
        replyToUser = json.optInt("reply_to_user");
        replyToComment = json.optInt("reply_to_comment");
        text = json.optString("text");

        postId = json.optInt("post_id");
        ownerId = json.optLong("owner_id");
        JSONObject obj1 = json.optJSONObject("thread");
        thread = (Thread) new Thread().deserialize(obj1);

        obj1 = json.optJSONObject("likes");
        if (obj1 != null) {
            likes = (Post.Likes) new Post.Likes().deserialize(obj1);
        }

        JSONArray array = json.optJSONArray("parents_stack");
        if (array != null) {
            parents_stack = new int[array.length()];
            for (int i = 0; i < parents_stack.length; i++) {
                parents_stack[i] = array.optInt(i);
            }
        }

        array = json.optJSONArray("attachments");
        if (array != null) {
            attachments = new Attachment[array.length()];
            for (int i = 0; i < attachments.length; i++) {
                JSONObject obj = array.optJSONObject(i);
                if (obj != null) {
                    attachments[i] = Attachment.parse(obj);
                }
            }
        }

        return this;
    }

    public String getLikeableType() {
        return "comment";
    }

    public long getLikeableOwnerId() {
        return ownerId;
    }

    public int getLikeableItemsId() {
        return id;
    }

    public boolean out() {
        return out;
    }

    public int id() {
        return id;
    }

    public long fromId() {
        return fromId;
    }

    public ImItem replyMessage() {
        return replyMessage;
    }

    public Attachment[] attachments() {
        return attachments;
    }

    public String text() {
        return text;
    }

    public long ownerId() {
        return ownerId;
    }

    public boolean hasForwadedMessages() {
        return false;
    }

    public boolean hasForwardedMessages() {
        return false;
    }

    public ImItem[] forwardedMessages() {
        return null;
    }

    public static class Thread extends VKSerializableObject {

        public int count;
        public Comment[] items;
        public boolean can_post, show_reply_button, groups_can_post;
        
        public boolean hasItems() {
            return items != null && items.length > 0;
        }

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }
            count = json.optInt("count");
            can_post = json.optBoolean("can_post");
            show_reply_button = json.optBoolean("show_reply_button");
            groups_can_post = json.optBoolean("groups_can_post");

            JSONArray array = json.optJSONArray("items");
            if (array != null) {
                items = new Comment[array.length()];
                for (int i = 0; i < items.length; i++) {
                    JSONObject obj = array.optJSONObject(i);
                    if (obj != null) {
                        items[i] = (Comment) new Comment().deserialize(obj);
                    }
                }
            }

            return this;
        }
    }
}
