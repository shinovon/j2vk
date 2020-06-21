package ru.curoviyxru.j2vk.api.objects;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.PageStorage;

import ru.curoviyxru.j2vk.api.objects.user.Page;

/**
 *
 * @author curoviyxru
 */
public class Conversation extends VKSerializableObject implements IHistoryConversation {

    public Peer peer;
    public int inRead, outRead, unreadCount;
    public boolean important, unanswered;
    public CanWrite canWrite;
    public ChatSettings chatSettings;
    public PushSettings pushSettings;

    public long getId() {
        return hasPeer() ? peer.id : 0;
    }
    
    public boolean isChannel() {
        return hasChatSettings() && chatSettings.isGroupChannel;
    }

    public boolean canWrite() {
        return hasCanWrite() && canWrite.allowed;
    }

    public boolean hasCanWrite() {
        return canWrite != null;
    }

    public long getLocalId() {
        return hasPeer() ? peer.localId : 0;
    }

    public static class PushSettings extends VKSerializableObject {

        public int disabled_until;
        public boolean disabled_forever, no_sound;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            disabled_until = json.optInt("disabled_until");
            disabled_forever = json.optBoolean("disabled_forever");
            no_sound = json.optBoolean("no_sound");

            return this;
        }
    }

    public boolean hasPushSettings() {
        return pushSettings != null;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        JSONObject obj = json.optJSONObject("peer");
        if (obj != null) {
            peer = (Peer) new Peer().deserialize(obj);
        }
        obj = json.optJSONObject("can_write");
        if (obj != null) {
            canWrite = (CanWrite) new CanWrite().deserialize(obj);
        }
        obj = json.optJSONObject("chat_settings");
        if (obj != null) {
            chatSettings = (ChatSettings) new ChatSettings().deserialize(obj);
        }
        obj = json.optJSONObject("push_settings");
        if (obj != null) {
            pushSettings = (PushSettings) new PushSettings().deserialize(obj);
        }

        inRead = json.optInt("in_read");
        outRead = json.optInt("out_read");
        unreadCount = json.optInt("unread_count");
        important = json.optBoolean("important");
        unanswered = json.optBoolean("unanswered");

        return this;
    }

    public boolean isPage() {
        return hasPeer() && (peer.peerType.equals(Peer.USER) || peer.peerType.equals(Peer.GROUP)) && !isChannel();
    }
    
    public boolean isChat() {
        return !isPage() && !isChannel();
    }

    public boolean hasPeer() {
        return peer != null;
    }

    public String getTitle() {
        Page poster = isPage() ? PageStorage.get(peer.id) : null;
        return isPage() ? poster != null ? poster.getName() : null : hasChatSettings() ? chatSettings.title : null;
    }

    public String getPhoto_50() {
        return hasPhoto_100() ? chatSettings.photo.photo_50 : null;
    }
    
    public String getPhoto_100() {
        return hasPhoto_100() ? chatSettings.photo.photo_100 : null;
    }

    public boolean hasPhoto_50() {
        return hasChatSettings() && chatSettings.hasPhoto() && chatSettings.photo.hasPhoto_50();
    }
    
    public boolean hasPhoto_100() {
        return hasChatSettings() && chatSettings.hasPhoto() && chatSettings.photo.hasPhoto_100();
    }

    public boolean hasChatSettings() {
        return chatSettings != null;
    }

    public static class ChatSettings extends VKSerializableObject {

        public boolean hasPinnedMessage() {
            return pinnedMessage != null;
        }
        //Неизвестный филд acl?
        //Неизестные филды - булины - can_send_money can_receive_money
        public int membersCount;
        public String title, state;
        public Message pinnedMessage;
        public ConversationPhoto photo;
        public boolean isGroupChannel;
        public long[] activeIds;
        public long[] adminIds;
        public int owner_id;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            owner_id = json.optInt("owner_id");
            membersCount = json.optInt("members_count");
            title = json.optString("title");
            state = json.optString("state");
            JSONObject obj = json.optJSONObject("pinned_message");
            if (obj != null) {
                pinnedMessage = (Message) new Message().deserialize(obj);
            }
            obj = json.optJSONObject("photo");
            if (obj != null) {
                photo = (ConversationPhoto) new ConversationPhoto().deserialize(obj);
            }
            isGroupChannel = json.optBoolean("is_group_channel");
            JSONArray array = json.optJSONArray("active_ids");
            if (array != null) {
                activeIds = new long[array.length()];
                for (int i = 0; i < activeIds.length; i++) {
                    activeIds[i] = array.optLong(i);
                }
            }
            array = json.optJSONArray("admin_ids");
            if (array != null) {
                adminIds = new long[array.length()];
                for (int i = 0; i < adminIds.length; i++) {
                    adminIds[i] = array.optLong(i);
                }
            }

            return this;
        }

        public boolean hasPhoto() {
            return photo != null;
        }

        public boolean isAdmin(long fromId) {
            if (owner_id == fromId) {
                return true;
            }

            if (!hasAdminIds()) {
                return false;
            }

            for (int i = 0; i < adminIds.length; i++) {
                if (fromId == adminIds[i]) {
                    return true;
                }
            }

            return false;
        }

        public boolean hasAdminIds() {
            return adminIds != null && adminIds.length > 0;
        }
    }

    public static class ConversationPhoto extends VKSerializableObject {

        public String photo_50, photo_100; //photo_200

        public final boolean hasPhoto_50() {
            return !isEmpty(photo_50); //TODO check for URL
        }
        
        public final boolean hasPhoto_100() {
            return !isEmpty(photo_100); //TODO check for URL
        }

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            photo_50 = json.optString("photo_50");
            photo_100 = json.optString("photo_100");

            return this;
        }
    }

    public static class CanWrite extends VKSerializableObject {

        public boolean allowed;
        public int reason;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            allowed = json.optBoolean("allowed");
            reason = json.optInt("reason");

            return this;
        }
    }

    public static class Peer extends VKSerializableObject {

        public static final String USER = "user",
                CHAT = "chat",
                GROUP = "group",
                EMAIL = "email";
        public long id, localId;
        public String peerType;

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            id = json.optLong("id");
            localId = json.optLong("local_id");
            peerType = json.optString("type");

            return this;
        }
    }
}
