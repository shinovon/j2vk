package ru.curoviyxru.j2vk;

import ru.curoviyxru.j2vk.api.objects.Account;

/**
 *
 * @author curoviyxru
 */
public class VKConstants {

    public static Account account;
    public static String apiUrl, oauthUrl, proxyUrl;
    public static ILogger logger;

    public static String apiUrl() {
        if (apiUrl == null) {
            return "https://api.vk.com/";
        }
        return apiUrl;
    }

    public static String oauthUrl() {
        if (oauthUrl == null) {
            return "https://oauth.vk.com/";
        }
        return oauthUrl;
    }
    public static IVKClient client;

    public static void debug(int i, String string) {
        if (logger != null) logger.log(string);
    }
    
    public static void debug(int i, Throwable string) {
        if (logger != null) logger.log(string);
    }
    
//    public static final String UserThumb = "https://vk.com/images/camera_";
//    public static final String UserThumb_50 = UserThumb + "50.png";
//    public static final String UserThumb_100 = UserThumb + "100.png";
//    public static final String DeactivatedThumb = "https://vk.com/images/deactivated_";
//    public static final String DeactivatedThumb_50 = DeactivatedThumb + "50.png";
//    public static final String DeactivatedThumb_100 = DeactivatedThumb + "100.png";
//    public static final String CommunityThumb = "https://vk.com/images/community_";
//    public static final String CommunityThumb_50 = CommunityThumb + "50.png";
//    public static final String CommunityThumb_100 = CommunityThumb + "100.png";
//    public static final String MultiChatThumb = "https://vk.com/images/icons/im_multichat_";
//    public static final String MultiChatThumb_50 = MultiChatThumb + "50.png";
//    public static final String MultiChatThumb_100 = MultiChatThumb + "100.png";
    public static final String fields = "can_message,ban_info,can_write_private_message,can_see_audio,followers,friend_status,common_count,blacklisted,blacklisted_by_me,fixed_post,ban_info,members_count,counters,photo_50,photo_100,text,views,reposts,likes,status,sex,online,online_app,online_mobile,last_seen,first_name_nom,first_name_gen,first_name_dat,first_name_acc,first_name_ins,first_name_abl,last_name_nom,last_name_gen,last_name_dat,last_name_acc,last_name_ins,last_name_abl,screen_name,image_status,emoji_status";
    public static final String extended_fields = "crop_photo,photo_50,photo_100,online,online_app,online_mobile,last_seen,sex,status,screen_name";
    public static final String full_user_fields = fields+","+extended_fields+",occupation,description,activity,age_limits,bdate,education,contacts,home_town,country,city,interests,books,activities,about,connections,relation,movies,music,games,tv,quotes,personal,site";
    
    public static final String api_version = "5.103"; //5.90 - last mp3 version, 5.103 - last stable?
    public static final String audio_api_version = "5.90"; //5.90 - last mp3 version, 5.103 - last stable?
    
    public static final String api_id = "2274003"; //Android
    //public static final String api_id = "2685278"; //Kate
    
    public static final String api_name = "vk4me"; //for OpenVK
    
    public static final String api_secret = "hHbZxrka2uZ6jB1inYsH"; //Android
    //public static final String api_secret = "lxhD8OD7dMsqtXIm5IUY"; //Kate
    
    public static final String api_useragent = "VKAndroidApp/5.40-3906 (Android 6.0.0; SDK 23; armeabi-v7a; LGE Nexus 5; ru; 1920x1080)"; //Android
    //public static final String api_useragent = "KateMobileAndroid/76 lite-495 (Android 6.0.0; SDK 23; arm64-v8a; Android Device; ru; 1920x1080)"; //Kate
    
    public static final String staticRefreshToken = "1rEw:APA91bFiaESDgQwk6GRoPKusaxRrNiyOkWn1zPdcYj9gMB9O-gE2kbAFHv1t1N9sE8aTRK-HqiR4ytjiGEMCLnJirXMU2_4Dl6l5Wp3ZYBFRG3XhfdW9LzNCONy2NSuGjJdoSwo6fcE-"; //Android
    //public static final String staticRefreshToken = ":APA91bFAM-gVwLCkCABy5DJPPRH5TNDHW9xcGu_OLhmdUSA8zuUsBiU_DexHrTLLZWtzWHZTT5QUaVkBk_GJVQyCE_yQj9UId3pU3vxvizffCPQISmh2k93Fs7XH1qPbDvezEiMyeuLDXb5ebOVGehtbdk_9u5pwUw"; //Kate
}
