package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.IHistoryConversation;
import ru.curoviyxru.j2vk.api.objects.Likeable;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class Post extends Attachment implements Likeable, IHistoryConversation {

    public String toString() {
        return "wall" + owner_id + "_" + post_id;
    }

    public String type;
    public long owner_id, from_id;
    public int date;
    public String post_type;
    public String text;
    public long signer_id;
    public boolean marked_as_ads; //bool int
    public Attachment[] attachments;
    public Comments comments;
    public Likes likes;
    public Reposts reposts;
    public int views_count;
    public boolean is_favorite;
    public int post_id;
    public Post[] copy_history;
    boolean isAdBool;

    public boolean isAd() {
        return isAdBool;
    }

    public boolean hasCopyHistory() {
        return copy_history != null && copy_history.length > 0;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        owner_id = json.has("owner_id") ? json.optLong("owner_id") : json.has("to_id") ? json.optLong("to_id") : json.optLong("source_id");
        from_id = json.optLong("from_id");
        type = json.optString("type");
        post_id = json.has("id") ? json.optInt("id") : json.optInt("post_id");
        date = json.optInt("date");
        text = json.optString("text");

        JSONObject obj = json.optJSONObject("comments");
        comments = (Comments) new Comments().deserialize(obj);
        obj = json.optJSONObject("likes");
        likes = (Likes) new Likes().deserialize(obj);
        obj = json.optJSONObject("reposts");
        reposts = (Reposts) new Reposts().deserialize(obj);
        obj = json.optJSONObject("views");
        if (obj != null) {
            views_count = obj.optInt("count");
        }
        post_type = json.optString("post_type");
        JSONArray array = json.optJSONArray("attachments");
        if (array != null) {
            attachments = new Attachment[array.length()];
            for (int i = 0; i < attachments.length; i++) {
                JSONObject jsonobjj = array.optJSONObject(i);
                if (jsonobjj != null) {
                    attachments[i] = Attachment.parse(jsonobjj);
                }
            }
        }
        array = json.optJSONArray("copy_history");
        if (array != null) {
            copy_history = new Post[array.length()];
            for (int i = 0; i < copy_history.length; i++) {
                JSONObject jsonobjj = array.optJSONObject(i);
                if (jsonobjj != null) {
                    copy_history[i] = (Post) new Post().deserialize(jsonobjj);
                }
            }
        }
        signer_id = json.has("from_id") ? json.optLong("from_id") : json.optLong("signer_id");
        marked_as_ads = fromInteger(json.optInt("marked_as_ads"));
        is_favorite = json.optBoolean("is_favorite");
        isAdBool =
                marked_as_ads
                || (!isEmpty(json.optString("ext_id")) && (!isEmpty(type) || type.equals(TYPE_POST)))
                || json.has("authors_rec")
                || json.has("app_widget")
                || json.has("promo_button")
                || json.has("friends_recomm")
                || json.has("live_recommended");

        return this;
    }

    public boolean hasAttachments() {
        return attachments != null && attachments.length > 0;
    }
    public static final String TYPE_POST = "post";

    public boolean hasText() {
        return !isEmpty(text);
    }

    public boolean hasSigner() {
        return signer_id != 0;
    }

    public String getLikeableType() {
        return TYPE_POST;
    }

    public long getLikeableOwnerId() {
        return owner_id;
    }

    public int getLikeableItemsId() {
        return post_id;
    }

    public boolean canWrite() {
        return comments != null && comments.can_post;
    }

    public boolean hasChatSettings() {
        return false;
    }

    public long getId() {
        return post_id;
    }

    public String getTitle() {
        return "";
    }

    public long getLocalId() {
        return post_id;
    }
    
    public boolean isChat() {
        return true;
    }

    public class Reposts extends VKSerializableObject {

        public int count;
        public boolean user_reposted; //bool int

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            count = json.optInt("count");
            user_reposted = fromInteger(json.optInt("user_reposted"));

            return this;
        }
    }

    public class Comments extends VKSerializableObject {

        public int count;
        public boolean can_post; //bool int
        public boolean groups_can_post; //bool int
        public boolean can_close, can_open;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            count = json.optInt("count");
            can_post = fromInteger(json.optInt("can_post"));
            groups_can_post = fromInteger(json.optInt("groups_can_post"));
            can_close = json.optBoolean("can_close");
            can_open = json.optBoolean("can_open");

            return this;
        }
    }

    public static class Likes extends VKSerializableObject {

        public int count;
        public boolean user_likes, can_like, can_publish; //bool int

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            count = json.optInt("count");
            user_likes = fromInteger(json.optInt("user_likes"));
            can_like = fromInteger(json.optInt("can_like"));
            can_publish = fromInteger(json.optInt("can_publish"));

            return this;
        }
    }
}
