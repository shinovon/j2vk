package ru.curoviyxru.j2vk.api.requests;

import java.util.Hashtable;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKObject;
import ru.curoviyxru.j2vk.api.objects.Account;
import ru.curoviyxru.j2vk.api.responses.VKResponse;
import ru.curoviyxru.j2vk.PageStorage;
import ru.curoviyxru.j2vk.VKConstants;
import ru.curoviyxru.j2vk.api.requests.audio.AudioGet;
import ru.curoviyxru.j2vk.api.requests.audio.AudioGetPlaylists;
import ru.curoviyxru.j2vk.api.requests.photos.PhotosSaveMessagesPhoto;
import ru.curoviyxru.j2vk.HTTPClient;

/**
 *
 * @author curoviyxru
 */
public class VKRequest extends VKObject {

    String method, token;
    Hashtable args;
    Class _plug;

    public final boolean hasToken() {
        return !isEmpty(token);
    }

    public final String getToken() {
        return token;
    }

    public final VKRequest setToken(String token) {
        this.token = token;
        setArgument("access_token", token);

        return this;
    }
    boolean forceProxy = false;

    public final VKRequest setForceProxy(boolean bool) {
        forceProxy = bool;
        setArgument("forceProxy", bool);

        return this;
    }

    public final Hashtable getArguments() {
        return args;
    }

    public final VKRequest setArgument(String key, Object val) {
        if (val != null && !isEmpty(val.toString()) && !isEmpty(key)) {
            args.put(key, val.toString());
        } else {
            removeArgument(key);
        }

        return this;
    }

    public final VKRequest setArgument(String key, boolean val) {
        return setArgument(key, String.valueOf(val));
    }

    public final VKRequest setArgument(String key, int val) {
        return setArgument(key, String.valueOf(val));
    }

    public final VKRequest setArgument(String key, long val) {
        return setArgument(key, String.valueOf(val));
    }

    public final VKRequest setArgument(String key, float val) {
        return setArgument(key, String.valueOf(val));
    }

    public final VKRequest removeArgument(String key) {
        if (!isEmpty(key) && args.containsKey(key)) {
            args.remove(key);
        }

        return this;
    }

    public VKResponse execute() {
        return execute(null, false);
    }

    public VKResponse execute(Account account, boolean tried) {
        if (account == null && VKConstants.account == null) {
            client.logout();
            return newEmptyResponse();
        }

        if (account == null) {
            account = VKConstants.account;
        }

        if (this instanceof VKAudioRequest && !account.isRefreshed()) {
            account.refreshToken();
        }
        if (this instanceof AudioGetPlaylists && !((AudioGetPlaylists) this).hasOwnerId()) {
            ((AudioGetPlaylists) this).setOwnerId(account.getId());
        }
        if (this instanceof AudioGet && !((AudioGet) this).hasOwnerId()) {
            ((AudioGet) this).setOwnerId(account.getId());
        }

        String result = HTTPClient.makeResponse(setToken(account.getToken()));

        JSONObject obj;
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            VKConstants.debug(0, e);
            obj = null;

            if (this instanceof PhotosSaveMessagesPhoto) {
                try {
                    obj = new JSONObject().put("response", new JSONArray(result));
                } catch (JSONException ex) {
                    obj = null;
                }
            }
        }
        VKResponse response = newEmptyResponse();

        if (obj == null || response == null) {
            return response;
        }
        response = (VKResponse) response.deserialize(obj);

        PageStorage.parse(response);
        
        if (!tried && (response.error_code == 25 || (this instanceof VKAudioRequest && result != null && result.indexOf("vk.com/mp3/audio_api_unavailable.mp3") != -1) || (this instanceof VKAudioRequest && result != null && result.indexOf("index.m3u8") != -1))) {
            account.refreshToken();
            return execute(account, true);
        }

        return response;

    }

    public final VKResponse newEmptyResponse() {
        try {
            return (VKResponse) _plug.newInstance();
        } catch (Exception e) {
            VKConstants.debug(0, e);
            return null;
        }
    }

    public VKRequest(Class responseClazz, String method) {
        _plug = responseClazz;
        args = new Hashtable();
        setArgument("v", api_version);
        if (!isEmpty(method)) {
            setMethod(method);
        }
    }

    public final VKRequest setMethod(String str) {
        method = str;

        return this;
    }

    public final String toStringWithoutDomain() {
        return method + "?" + formatArgs(this);
    }

    public final String getArgument(String key) {
        if (!isEmpty(key) && args.containsKey(key)) {
            return (String) args.get(key);
        }

        return null;
    }

    public final boolean hasMethod() {
        return !isEmpty(method);
    }

    public final boolean hasArguments() {
        return args.size() > 0;
    }

    public String toString() {
        return apiUrl() + "method/" + toStringWithoutDomain();
    }
}
