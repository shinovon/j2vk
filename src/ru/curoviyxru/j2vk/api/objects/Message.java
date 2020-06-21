package ru.curoviyxru.j2vk.api.objects;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.Conversation.ConversationPhoto;
import ru.curoviyxru.j2vk.api.objects.user.Page;
import ru.curoviyxru.j2vk.api.objects.user.User;
import ru.curoviyxru.j2vk.api.requests.users.UsersGet;
import ru.curoviyxru.j2vk.PageStorage;
import ru.curoviyxru.j2vk.TextUtil;

public class Message extends VKSerializableObject implements ImItem {

    public long peerId, fromId, randomId;
    public int id, date, updateTime, conversationMessageId;
    public Attachment[] attachments;
    public boolean important, out;
    public Message[] fwdMessages;
    public Message replyMessage;
    public ChatAction action;
    public String text;
    //public Geo geo;
    //public String ref;
    //public String refSource;
    //public String rayload;

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        updateTime = json.optInt("update_time");
        id = json.optInt("id");
        out = fromInteger(json.optInt("out"));
        conversationMessageId = json.optInt("conversation_message_id");
        date = json.optInt("date");
        peerId = json.optLong("peer_id");
        fromId = json.optLong("from_id");
        text = json.optString("text");

        randomId = json.optLong("random_id");
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
        important = json.optBoolean("important");
        //JSONObject obj = json.optJSONObject("geo");
        //if (obj != null) {
        //    geo = (Geo) new Geo().deserialize(obj);
        //}
        array = json.optJSONArray("fwd_messages");
        if (array != null) {
            fwdMessages = new Message[array.length()];
            for (int i = 0; i < fwdMessages.length; i++) {
                JSONObject obj1 = array.optJSONObject(i);
                if (obj1 != null) {
                    fwdMessages[i] = (Message) new Message().deserialize(obj1);
                }
            }
        }
        JSONObject obj = json.optJSONObject("reply_message");
        if (obj != null) {
            replyMessage = (Message) new Message().deserialize(obj);
        }
        obj = json.optJSONObject("action");
        if (obj != null) {
            action = (ChatAction) new ChatAction().deserialize(obj);
        }

        return this;
    }

    public boolean hasChatAction() {
        return action != null && !isEmpty(action.type);
    }

    public boolean hasForwardedMessages() {
        return fwdMessages != null && fwdMessages.length > 0;
    }

    public boolean hasReplyMessage() {
        return replyMessage != null;
    }

    public static String LOCALE_YOU = "Вы";
    public static String LOCALE_CREATED_IT = "создало беседу";
    public static String LOCALE_CREATED_M = "создал беседу";
    public static String LOCALE_CREATED_F = "создала беседу";
    public static String LOCALE_JOINED_IT = "присоединилось";
    public static String LOCALE_JOINED_M = "присоединился";
    public static String LOCALE_JOINED_F = "присоединилась";
    public static String LOCALE_INVITED_IT = "пригласило";
    public static String LOCALE_INVITED_M = "пригласил";
    public static String LOCALE_INVITED_F = "пригласила";
    public static String LOCALE_TO_CHAT = "к беседе";
    public static String LOCALE_TO_CHAT_VIA_LINK = "к беседе по ссылке";
    public static String LOCALE_FROM_CHAT = "из беседы";
    public static String LOCALE_KICKED_IT = "исключило";
    public static String LOCALE_KICKED_M = "исключил";
    public static String LOCALE_KICKED_F = "исключила";
    public static String LOCALE_LEFT_IT = "вышло";
    public static String LOCALE_LEFT_M = "вышел";
    public static String LOCALE_LEFT_F = "вышла";
    public static String LOCALE_EMPTY = "*пусто*";
    public static String LOCALE_ATTACH = "[Вложение]";
    public static String LOCALE_ATTACHS = "[Вложения]";
    public static String LOCALE_FORWARD = "[Пересланное]";
    public static String LOCALE_FORWARDS = "[Пересланные]";
    public static String LOCALE_REPLY = "[Ответ]";
    public static String LOCALE_CHAT_TITLE_TO = "название беседы на";
    public static String LOCALE_CHAT_PHOTO = "фото беседы";
    public static String LOCALE_MESSAGE = "сообщение";
    public static String LOCALE_UPDATED_IT = "обновило";
    public static String LOCALE_UPDATED_M = "обновил";
    public static String LOCALE_UPDATED_F = "обновила";
    public static String LOCALE_REMOVED_IT = "удалило";
    public static String LOCALE_REMOVED_M = "удалил";
    public static String LOCALE_REMOVED_F = "удалила";
    public static String LOCALE_PINNED_IT = "закрепило";
    public static String LOCALE_PINNED_M = "закрепил";
    public static String LOCALE_PINNED_F = "закрепила";
    public static String LOCALE_UNPINNED_IT = "открепило";
    public static String LOCALE_UNPINNED_M = "открепил";
    public static String LOCALE_UNPINNED_F = "открепила";
    
    public String toString(boolean showName, boolean isPMs, boolean shortName, boolean mentionReply, boolean mentionFwd) {
        String str2 = "";

        if (showName) {
            Page p = PageStorage.get(fromId);
            if (!isPMs) {
                str2 += out ? LOCALE_YOU : p != null ? shortName ? p.getMessageTitle() : p.getName() : "";
            } else {
                str2 += out ? LOCALE_YOU : "";
            }
            if (!isPMs || out) {
                str2 += ": ";
            }
        }

        if (hasChatAction()) {
            Page p = PageStorage.get(fromId);
            String str1 = "";
            boolean myself = action.memberId == fromId;
            if (showName) {
                str1 += p != null ? !shortName ? p.getName() : p.getMessageTitle() : "";
                str1 += " ";
            }
            if (action.type.equals(ChatAction.chat_create)) {
                if (p != null) {
                    str1 += p.isGroup ? LOCALE_CREATED_IT : ((User) p).sex == User.FEMALE ? LOCALE_CREATED_F : LOCALE_CREATED_M;
                }
                str1 += " \"" + action.text + "\"";
            } else if (action.type.equals(ChatAction.chat_invite_user)) {
                if (p != null) {
                    if (!myself) {
                        str1 += p.isGroup ? LOCALE_INVITED_IT : ((User) p).sex == User.FEMALE ? LOCALE_INVITED_F : LOCALE_INVITED_M;
                    } else {
                        str1 += p.isGroup ? LOCALE_JOINED_IT : ((User) p).sex == User.FEMALE ? LOCALE_JOINED_F : LOCALE_JOINED_M;
                    }
                }
                if (!myself) {
                    p = PageStorage.get(action.memberId);
                    if (p != null) {
                        str1 += " " + (shortName ? p.getMessageTitle(UsersGet.ACC) : p.getName(UsersGet.ACC));
                    }
                } else {
                    str1 += " " + LOCALE_TO_CHAT;
                }
            } else if (action.type.equals(ChatAction.chat_kick_user)) {
                if (p != null) {
                    if (!myself) {
                        str1 += p.isGroup ? LOCALE_KICKED_IT : ((User) p).sex == User.FEMALE ? LOCALE_KICKED_F : LOCALE_KICKED_M;
                    } else {
                        str1 += p.isGroup ? LOCALE_LEFT_IT : ((User) p).sex == User.FEMALE ? LOCALE_LEFT_F : LOCALE_LEFT_M;
                    }
                }
                if (!myself) {
                    p = PageStorage.get(action.memberId);
                    if (p != null) {
                        str1 += " " + (shortName ? p.getMessageTitle(UsersGet.ACC) : p.getName(UsersGet.ACC));
                    }
                } else {
                    str1 += " " + LOCALE_FROM_CHAT;
                }
            } else if (action.type.equals(ChatAction.chat_invite_user_by_link)) {
                if (p != null) {
                    str1 += p.isGroup ? LOCALE_JOINED_IT : ((User) p).sex == User.FEMALE ? LOCALE_JOINED_F : LOCALE_JOINED_M;
                }
                str1 += " " + LOCALE_TO_CHAT_VIA_LINK;
            } else if (action.type.equals(ChatAction.chat_photo_remove)) {
                if (p != null) {
                    str1 += p.isGroup ? LOCALE_REMOVED_IT : ((User) p).sex == User.FEMALE ? LOCALE_REMOVED_F : LOCALE_REMOVED_M;
                }
                str1 += " " + LOCALE_CHAT_PHOTO;
            } else if (action.type.equals(ChatAction.chat_photo_update)) {
                if (p != null) {
                    str1 += p.isGroup ? LOCALE_UPDATED_IT : ((User) p).sex == User.FEMALE ? LOCALE_UPDATED_F : LOCALE_UPDATED_M;
                }
                str1 += " " + LOCALE_CHAT_PHOTO;
            } else if (action.type.equals(ChatAction.chat_title_update)) {
                if (p != null) {
                    str1 += p.isGroup ? LOCALE_UPDATED_IT : ((User) p).sex == User.FEMALE ? LOCALE_UPDATED_F : LOCALE_UPDATED_M;
                }
                str1 += " " + LOCALE_CHAT_TITLE_TO + " \"" + action.text + "\"";
            } else if (action.type.equals(ChatAction.chat_unpin_message)) {
                if (p != null) {
                    str1 += p.isGroup ? LOCALE_UNPINNED_IT : ((User) p).sex == User.FEMALE ? LOCALE_UNPINNED_F : LOCALE_UNPINNED_M;
                }
                str1 += " " + LOCALE_MESSAGE;
            } else if (action.type.equals(ChatAction.chat_pin_message)) {
                if (p != null) {
                    str1 += p.isGroup ? LOCALE_PINNED_IT : ((User) p).sex == User.FEMALE ? LOCALE_PINNED_F : LOCALE_PINNED_M;
                }
                str1 += " " + LOCALE_MESSAGE;
                if (!isEmpty(action.message)) {
                    str1 += " \"" + (action.message.length() > 24 ? action.message.substring(0, 24) + "..." : action.message) + "\"";
                }
            }
            return str1;
        } else {
            if (mentionReply && hasReplyMessage()) {
                Page u = PageStorage.get(replyMessage.fromId);
                if (u != null) {
                    str2 += LOCALE_REPLY + "\n";
                }
            }
            if (hasAttachments()) {
                if (this.attachments.length > 1) {
                    str2 += LOCALE_ATTACHS + "\n";
                } else {
                    str2 += LOCALE_ATTACH + "\n";
                }
            }
            if (mentionFwd && hasForwardedMessages()) {
                if (this.fwdMessages.length > 1) {
                    str2 += LOCALE_FORWARDS + "\n";
                } else {
                    str2 += LOCALE_FORWARD + "\n";
                }
            }

            if (!TextUtil.isNullOrEmpty(text)) {
                str2 += text.trim();
            } else if (!hasAttachments() && (mentionFwd && !hasForwardedMessages())) {
                str2 += LOCALE_EMPTY;
            }
        }

        return str2;
    }

    public String toString() {
        return toString(false, false, false, true, true);
    }

    public boolean hasAttachments() {
        return attachments != null && attachments.length > 0;
    }

    public int getLastTime() {
        return updateTime > date ? updateTime : date;
    }

    public boolean hasText() {
        return !TextUtil.isNullOrEmpty(text);
    }

    public boolean canEdDel() {
        return ((System.currentTimeMillis() / 1000) - date) / 86400 < 1;
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
        return peerId;
    }

    public boolean isDeleted() {
        return false;
    }

    public ImItem[] forwardedMessages() {
        return fwdMessages;
    }

    public static class ChatAction extends VKSerializableObject {

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            text = json.optString("text");
            email = json.optString("email");
            type = json.optString("type");
            memberId = json.optInt("member_id");
            message = json.optString("message");
            JSONObject obj = json.optJSONObject("photo");
            if (obj != null) {
                photo = (ConversationPhoto) new ConversationPhoto().deserialize(obj);
            }

            return this;
        }
        public String text, email, type, message;
        public int memberId;
        public ConversationPhoto photo;

        public boolean equals(String str) {
            return !isEmpty(type) && !isEmpty(str) && type.equals(str);
        }
        public static final String chat_photo_update = "chat_photo_update",
                chat_photo_remove = "chat_photo_remove",
                chat_create = "chat_create",
                chat_title_update = "chat_title_update",
                chat_invite_user = "chat_invite_user",
                chat_kick_user = "chat_kick_user",
                chat_pin_message = "chat_pin_message",
                chat_unpin_message = "chat_unpin_message",
                chat_invite_user_by_link = "chat_invite_user_by_link";
    }
}
