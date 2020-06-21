package ru.curoviyxru.j2vk.api.objects;

import ru.curoviyxru.j2vk.api.objects.attachments.Comment;
import ru.curoviyxru.j2vk.api.objects.attachments.Post;
import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Photo;
import ru.curoviyxru.j2vk.api.objects.attachments.Video;

/**
 *
 * @author curoviyxru
 */
public class Notification extends VKSerializableObject {

    String type;
    int date;
    NotificationFeedback[] feedback;
    VKSerializableObject parent;
    NotificationReply reply;
    
    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) return this;
        
        type = json.optString("type");
        date = json.optInt("date");
        
        JSONObject obj = json.optJSONObject("parent");
        if (obj != null) {
            if (type.equals("mention_comments") || 
                    type.equals("comment_post") || 
                    type.equals("like_post") || 
                    type.equals("copy_post")) {
                parent = (VKSerializableObject) new Post().deserialize(obj);
            } else if (type.equals("comment_photo") || 
                    type.equals("like_photo") || 
                    type.equals("copy_photo") || 
                    type.equals("mention_comment_photo")) {
                parent = (VKSerializableObject) new Photo().deserialize(obj);
            } else if (type.equals("comment_video") || 
                    type.equals("like_video") || 
                    type.equals("copy_video") || 
                    type.equals("mention_comment_video")) {
                parent = (VKSerializableObject) new Video().deserialize(obj);
            } else if (type.equals("reply_comment") || 
                    type.equals("reply_comment_photo") || 
                    type.equals("reply_comment_video") || 
                    type.equals("reply_comment_market") ||
                    type.equals("like_comment") || 
                    type.equals("like_comment_photo") || 
                    type.equals("like_comment_video") || 
                    type.equals("like_comment_topic")) {
                parent = (VKSerializableObject) new Comment().deserialize(obj);
            } else if (type.equals("reply_topic")) {
                parent = (VKSerializableObject) new Topic().deserialize(obj);
            }
        }
        obj = json.optJSONObject("feedback");
        if (obj != null) {
            JSONArray feedbackItems = obj.optJSONArray("items");
            if (feedbackItems == null) {
                feedback = new NotificationFeedback[] {(NotificationFeedback) new NotificationFeedback().deserialize(obj)};
            } else {
                feedback = new NotificationFeedback[feedbackItems.length()];
                for (int i = 0; i < feedback.length; ++i) {
                    JSONObject item1 = feedbackItems.optJSONObject(i);
                    if (item1 != null) feedback[i] = (NotificationFeedback) new NotificationFeedback().deserialize(item1);
                }
            }
        }
        obj = json.optJSONObject("reply");
        if (obj != null) 
            reply = (NotificationReply) new NotificationReply().deserialize(obj);
        
        return this;
    }
    
    public static class NotificationFeedback extends VKSerializableObject {

        int id;
        long to_id;
        long from_id;
        String text;
        Post.Likes likes;
        Attachment[] attachments;
        Geo geo;
        
        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) return this;
            
            id = json.optInt("id");
            to_id = json.optLong("to_id");
            from_id = json.optLong("from_id");
            text = json.optString("text");
            JSONObject obj = json.optJSONObject("likes");
            if (obj != null) likes = (Post.Likes) new Post.Likes().deserialize(obj);
            obj = json.optJSONObject("geo");
            if (obj != null) geo = (Geo) new Geo().deserialize(obj);
            JSONArray array = json.optJSONArray("attachments");
            if (array != null) {
                attachments = new Attachment[array.length()];
                for (int i = 0; i < attachments.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        attachments[i] = Attachment.parse(obj1);
                    }
                }
            }
            
            return this;
        }
        
    }
    
    public static class NotificationReply extends VKSerializableObject {

        int id, date;
        String text;
        
        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) return this;
            
            id = json.optInt("id");
            date = json.optInt("date");
            text = json.optString("text");
            
            return this;
        }
        
    }
    
}
