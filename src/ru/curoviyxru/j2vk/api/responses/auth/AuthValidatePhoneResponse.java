package ru.curoviyxru.j2vk.api.responses.auth;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class AuthValidatePhoneResponse extends VKResponse {

    public int delay;

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject response = json.optJSONObject("response");
        if (response != null) {
            delay = response.optInt("delay");
        }

        return this;
    }
}
