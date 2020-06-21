package ru.curoviyxru.j2vk.api.objects;

import java.util.Hashtable;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.VKConstants;
import ru.curoviyxru.j2vk.api.objects.attachments.Sticker;
import ru.curoviyxru.j2vk.api.objects.attachments.Photo;
import ru.curoviyxru.j2vk.api.objects.attachments.Audio;
import ru.curoviyxru.j2vk.api.objects.attachments.AudioMessage;
import ru.curoviyxru.j2vk.api.objects.attachments.AudioPlaylist;
import ru.curoviyxru.j2vk.api.objects.attachments.Comment;
import ru.curoviyxru.j2vk.api.objects.attachments.Document;
import ru.curoviyxru.j2vk.api.objects.attachments.GenericAttachment;
import ru.curoviyxru.j2vk.api.objects.attachments.Gift;
import ru.curoviyxru.j2vk.api.objects.attachments.Graffiti;
import ru.curoviyxru.j2vk.api.objects.attachments.Link;
import ru.curoviyxru.j2vk.api.objects.attachments.Post;
import ru.curoviyxru.j2vk.api.objects.attachments.Story;
import ru.curoviyxru.j2vk.api.objects.attachments.Video;

/**
 *
 * @author curoviyxru
 */
public abstract class Attachment extends VKSerializableObject {

    public abstract String toString();
    public static Hashtable classTable;

    public static void initTable() {
        classTable = new Hashtable();

        classTable.put("photo", Photo.class);
        classTable.put("sticker", Sticker.class);
        classTable.put("audio", Audio.class);
        classTable.put("graffiti", Graffiti.class);
        classTable.put("doc", Document.class);
        classTable.put("audio_message", AudioMessage.class);
        classTable.put("audio_playlist", AudioPlaylist.class);
        classTable.put("story", Story.class);
        classTable.put("video", Video.class);
        classTable.put("gift", Gift.class);
        classTable.put("link", Link.class);
        classTable.put("wall", Post.class);
        classTable.put("wall_reply", Comment.class);
    }

    public static Attachment parse(JSONObject json) {
        if (classTable == null) initTable();

        String type = json.optString("type");
        if (!isEmpty(type) && classTable.containsKey(type)) {
            Class clazz = (Class) classTable.get(type);
            if (clazz != null) {
                try {
                    return (Attachment) ((Attachment) clazz.newInstance()).deserialize(json.optJSONObject(type));
                } catch (Exception e) {
                    VKConstants.debug(0, e);
                }
            }
        } else {
            return (Attachment) new GenericAttachment(type).deserialize(json.optJSONObject(type));
        }

        return null;
    }
}
