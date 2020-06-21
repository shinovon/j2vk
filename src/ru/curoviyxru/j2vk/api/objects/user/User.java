package ru.curoviyxru.j2vk.api.objects.user;

import java.util.Hashtable;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Photo;

/**
 *
 * @author curoviyxru
 */
public class User extends Page {

    public static boolean reverseShortName = false;
    public long id;
    public int sex;
    public String first_name, last_name, deactivated, photo_50, photo_100, status, lastC, screen_name;
    public boolean is_closed, can_access_closed, blacklisted, blacklisted_by_me;
    public int online, online_mobile, online_app, last_seen_time, last_seen_platform;
    public int friend_status;
    Hashtable first_name_table = new Hashtable();
    Hashtable last_name_table = new Hashtable();
    public static final int MALE = 2, FEMALE = 1, UNKNOWN = 0;
    public static final String DELETED = "deleted", BANNED = "banned";
    public static final int IS_FRIEND = 3, NOT_FRIEND = 0, FRIEND_SENT = 1, FRIEND_INVITE = 2;
    public boolean can_see_audio;
    public boolean can_write_private_message;
    public int audios_count, friends_count, groups_count, online_friends, mutual_friends, followers_count, photos_count, albums_count, videos_count;
    public Photo crop_photo;
    
    public String bdate; //bdate
    public String university_name, faculty_name; //education
    public int graduation; //education
    public String mobile_phone, home_phone; //contacts
    public String home_town; //home_town
    public String country_title; //country
    public String city_title; //city
    public String interests; //interests
    public String books; //books
    public String activities; //activities
    public String about; //about
    public JSONObject connections; //connections
    public int relation; //relation
    public String movies; //movies
    public String music; //music
    public String games; //games
    public String tv; //tv
    public String quotes; //quotes
    public JSONObject personal; //personal
    public String site; //site
    public String occupation_type, occupation_name; //occupation
    public int relation_partner_id;

    public final String getStatus() {
        return status;
    }

    public User() {
        super(false);
    }

    public final String getPhoto_50() {
        return !isEmpty(photo_50) ? photo_50 : null;
    }

    public final long getId() {
        return id;
    }

    public final boolean hasFirstName() {
        return !isEmpty(first_name);
    }

    public final boolean hasLastName() {
        return !isEmpty(last_name);
    }

    public final boolean hasDeactivated() {
        return !isEmpty(deactivated);
    }

    public final boolean isClosed() {
        return is_closed;
    }

    public final boolean canAccessClosed() {
        return can_access_closed;
    }

    public final VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optLong("id");
        sex = json.optInt("sex");
        status = json.optString("status");
        first_name = json.optString("first_name");
        last_name = json.optString("last_name");
        deactivated = json.optString("deactivated");
        photo_50 = json.optString("photo_50");
        photo_100 = json.optString("photo_100");
        is_closed = json.optBoolean("is_closed");
        can_access_closed = json.optBoolean("can_access_closed");
        online = json.optInt("online");
        online_app = json.optInt("online_app");
        online_mobile = json.optInt("online_mobile");
        friend_status = json.optInt("friend_status");
        blacklisted = fromInteger(json.optInt("blacklisted"));
        blacklisted_by_me = fromInteger(json.optInt("blacklisted_by_me"));
        can_see_audio = fromInteger(json.optInt("can_see_audio"));
        can_write_private_message = fromInteger(json.optInt("can_write_private_message"));
        screen_name = json.optString("screen_name");

        JSONObject obj = json.optJSONObject("counters");
        if (obj != null) {
            audios_count = obj.optInt("audios");
            friends_count = obj.optInt("friends");
            groups_count = obj.optInt("groups");
            online_friends = obj.optInt("online_friends");
            mutual_friends = obj.optInt("mutual_friends");
            followers_count = obj.optInt("followers");
            photos_count = obj.optInt("photos");
            albums_count = obj.optInt("albums");
            videos_count = obj.optInt("videos");
        }

        obj = json.optJSONObject("last_seen");
        if (obj != null) {
            last_seen_time = obj.optInt("time");
            last_seen_platform = obj.optInt("platform");
        }

        first_name_table.put("first_name_nom", json.optString("first_name_nom", first_name));
        first_name_table.put("first_name_gen", json.optString("first_name_gen", first_name));
        first_name_table.put("first_name_dat", json.optString("first_name_dat", first_name));
        first_name_table.put("first_name_acc", json.optString("first_name_acc", first_name));
        first_name_table.put("first_name_ins", json.optString("first_name_ins", first_name));
        first_name_table.put("first_name_abl", json.optString("first_name_abl", first_name));

        last_name_table.put("last_name_nom", json.optString("last_name_nom", last_name));
        last_name_table.put("last_name_gen", json.optString("last_name_gen", last_name));
        last_name_table.put("last_name_dat", json.optString("last_name_dat", last_name));
        last_name_table.put("last_name_acc", json.optString("last_name_acc", last_name));
        last_name_table.put("last_name_ins", json.optString("last_name_ins", last_name));
        last_name_table.put("last_name_abl", json.optString("last_name_abl", last_name));
        
        bdate = json.optString("bdate");
        obj = json.optJSONObject("education");
        if (obj != null) {
            university_name = obj.optString("university_name");
            faculty_name = obj.optString("faculty_name");
            graduation = obj.optInt("graduation");
        }
        obj = json.optJSONObject("contacts");
        if (obj != null) {
            mobile_phone = obj.optString("mobile_phone");
            home_phone = obj.optString("home_phone");
        }
        home_town = json.optString("home_town");
        obj = json.optJSONObject("country");
        if (obj != null) {
            country_title = obj.optString("title");
        }
        obj = json.optJSONObject("city");
        if (obj != null) {
            country_title = obj.optString("title");
        }
        interests = json.optString("interests");
        books = json.optString("books");
        activities = json.optString("activities");
        about = json.optString("about");
        relation = json.optInt("relation");
        movies = json.optString("movies");
        music = json.optString("music");
        games = json.optString("games");
        tv = json.optString("tv");
        quotes = json.optString("quotes");
        site = json.optString("site");
        connections = json.optJSONObject("connections");
        personal = json.optJSONObject("personal");
        obj = json.optJSONObject("occupation");
        if (obj != null) {
            occupation_type = obj.optString("type");
            occupation_name = obj.optString("name");
        }
        
        obj = json.optJSONObject("crop_photo");
        if (obj != null) {
            obj = obj.optJSONObject("photo");
            if (obj != null) {
                crop_photo = (Photo) new Photo().deserialize(obj);
            }
        }
        
        obj = json.optJSONObject("relation_partner");
        if (obj != null) {
            relation_partner_id = obj.optInt("id");
        }
        
        return this;
    }
    
    public String getPhoto_100() {
        return !isEmpty(photo_100) ? photo_100 : getPhoto_50();
    }

    public String getShortName(String nom) {
        return reverseShortName ? getShortR(nom) : getShortO(nom);
    }

    public final String getName(String nom) {
        return isEmpty(nom) || !last_name_table.containsKey("last_name_" + nom) || !first_name_table.containsKey("first_name_" + nom) ? first_name + " " + last_name : first_name_table.get("first_name_" + nom) + " " + last_name_table.get("last_name_" + nom);
    }

    public String getFirstName(String s) {
        return first_name_table.containsKey("last_name_" + s) ? first_name_table.get("last_name_" + s) != null ? (String) first_name_table.get("last_name_" + s) : first_name : first_name;
    }

    public String getShortO(String nom) {
        String ln = (String) last_name_table.get("last_name_" + nom);
        String fn = (String) first_name_table.get("first_name_" + nom);
        if (ln == null) {
            ln = last_name;
        }
        if (fn == null) {
            fn = first_name;
        }

        return (fn != null && fn.length() > 0 ? fn.charAt(0) + ". " : "") + ln;
    }

    public String getShortR(String nom) {
        String ln = (String) last_name_table.get("last_name_" + nom);
        String fn = (String) first_name_table.get("first_name_" + nom);
        if (ln == null) {
            ln = last_name;
        }
        if (fn == null) {
            fn = first_name;
        }

        return fn + (ln != null && ln.length() > 0 ? " " + ln.charAt(0) + "." : "");
    }

    public String getNickname() {
        return screen_name;
    }
}
