package ru.curoviyxru.j2vk.api.responses.auth;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class AuthRefreshTokenResponse extends VKResponse {

    public String token;

    public boolean hasToken() {
        return !isEmpty(token);
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            token = response.optString("token");
        }

        return this;
    }
}
