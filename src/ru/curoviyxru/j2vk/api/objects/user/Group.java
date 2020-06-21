package ru.curoviyxru.j2vk.api.objects.user;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Photo;

/**
 *
 * @author curoviyxru
 */
public class Group extends Page {
    
    public static final int OTHER_REASON = 0, SPAM_REASON = 1, INSULTING_REASON = 2, OFFTOPPING_REASON = 4, OBSCENING_REASON = 3, MODERATOR = 1, EDITOR = 2, ADMINISTATOR = 3, OPENED = 0, CLOSED = 1, PRIVATE = 2;

    public long id;
    public int is_closed, admin_level, invited_by;
    public String name, screen_name, deactivated, type, photo_50, photo_100, status;
    public boolean blacklisted, is_member, is_admin, is_advertiser, can_message; //boolean integers
    public int ban_end_date, members_count, ban_reason, audios_count, albums_count, photos_count, topics_count, docs_count, videos_count;
    public String ban_comment;
    
    public String description;
    public String activity;
    public int age_limits;
    public String city_title; //city
    public String country_title; //country
    public String site;
    public Photo crop_photo;
    
    public Group() {
        super(true);
    }

    public final String getStatus() {
        return status;
    }

    public final String getPhoto_50() {
        return !isEmpty(photo_50) ? photo_50 : null;
    }

    public final String getPhoto_100() {
        return !isEmpty(photo_100) ? photo_100 : getPhoto_50();
    }

    public final String getName(String s) {
        return name;
    }

    public final long getId() {
        return -id;
    }

    public final VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optLong("id");
        is_closed = json.optInt("is_closed");
        admin_level = json.optInt("admin_level");
        invited_by = json.optInt("invited_by");
        name = json.optString("name");
        screen_name = json.optString("screen_name");
        deactivated = json.optString("deactivated");
        type = json.optString("type");
        photo_50 = json.optString("photo_50");
        photo_100 = json.optString("photo_100");
        members_count = json.optInt("members_count");
        is_member = fromInteger(json.optInt("is_member"));
        is_admin = fromInteger(json.optInt("is_admin"));
        is_advertiser = fromInteger(json.optInt("is_advertiser"));
        status = json.optString("status");
        can_message = json.optBoolean("can_message");
        JSONObject obj = json.optJSONObject("ban_info");
        if (obj != null) {
            blacklisted = true;
            ban_comment = obj.optString("comment");
            ban_reason = obj.optInt("reason", -1);
            ban_end_date = obj.optInt("end_date");
        }
        obj = json.optJSONObject("counters");
        if (obj != null) {
            audios_count = obj.optInt("audios");
            albums_count = obj.optInt("albums");
            photos_count = obj.optInt("photos");
            videos_count = obj.optInt("videos");
            topics_count = obj.optInt("topics");
            docs_count = obj.optInt("docs");
        }

        description = json.optString("description");
        activity = json.optString("activity");
        age_limits = json.optInt("age_limits");
        site = json.optString("site");
        obj = json.optJSONObject("city");
        if (obj != null) {
            city_title = obj.optString("title");
        }
        obj = json.optJSONObject("country");
        if (obj != null) {
            country_title = obj.optString("title");
        }
        
        obj = json.optJSONObject("crop_photo");
        if (obj != null) {
            obj = obj.optJSONObject("photo");
            if (obj != null) {
                crop_photo = (Photo) new Photo().deserialize(obj);
            }
        }
        
        return this;
    }
    
    public String getShortName(String s) {
        return name == null || name.length() < 1 ? name : name.charAt(0) + ".";
    }

    public String getFirstName(String s) {
        return name;
    }

    public String getNickname() {
        return screen_name;
    }
}
