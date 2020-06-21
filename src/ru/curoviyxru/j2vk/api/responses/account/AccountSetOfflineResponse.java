package ru.curoviyxru.j2vk.api.responses.account;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class AccountSetOfflineResponse extends VKResponse {
    public int response;

    public boolean isSuccessful() {
        return super.isSuccessful() && response != 0;
    }

    public VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        response = json.optInt("response");

        return this;
    }
}
