package ru.curoviyxru.j2vk.api.requests.auth;

import org.json.me.JSONException;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Account;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.VKResponse;
import ru.curoviyxru.j2vk.api.responses.auth.AuthValidatePhoneResponse;
import ru.curoviyxru.j2vk.HTTPClient;
import ru.curoviyxru.j2vk.VKConstants;

/**
 *
 * @author curoviyxru
 */
public class AuthValidatePhone extends VKRequest {

    public AuthValidatePhone(String sid) {
        super(AuthValidatePhoneResponse.class, "auth.validatePhone");
        setArgument("api_id", api_id);
        setArgument("client_id", api_id);
        setArgument("client_secret", api_secret);
        setArgument("sid", sid);
    }

    public final boolean isReadyToSend() {
        return true;
    }

    public VKResponse execute(Account ac) {
        return execute();
    }

    public VKResponse execute() {
        String result = HTTPClient.makeResponse(this);

        JSONObject obj;
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            VKConstants.debug(0, e);
            obj = null;
        }
        VKResponse response = newEmptyResponse();

        if (obj == null || response == null) {
            return response;
        }
        response = (VKResponse) response.deserialize(obj);
        return response;
    }
}
