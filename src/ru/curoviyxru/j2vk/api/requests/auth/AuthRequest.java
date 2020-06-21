package ru.curoviyxru.j2vk.api.requests.auth;

import org.json.me.JSONException;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Account;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.VKResponse;
import ru.curoviyxru.j2vk.api.responses.auth.AuthResponse;
import ru.curoviyxru.j2vk.HTTPClient;
import ru.curoviyxru.j2vk.VKConstants;

/**
 *
 * @author curoviyxru
 */
public class AuthRequest extends VKRequest {

    public String email;
    public String password;
    String tfa_code;
    String captcha_sid;
    String captcha_key;
    boolean force_sms = false;

    public AuthRequest(String email, String password) {
        super(AuthResponse.class, "token");
        setArgument("libverify_support", 1);
        setArgument("grant_type", "password");
        setArgument("client_id", api_id);
        setArgument("client_name", api_name);
        setArgument("client_secret", api_secret);
        setArgument("scope", "all");
        setArgument("2fa_supported", 1);
        setEmail(email);
        setPassword(password);
    }

    public final AuthRequest set2FACode(String code) {
        tfa_code = code;
        setArgument("code", tfa_code);

        return this;
    }

    public final boolean isSMSForced() {
        return force_sms;
    }

    public final AuthRequest setSMSForced(boolean bool) {
        force_sms = bool;
        setArgument("force_sms", force_sms ? "1" : null);

        return this;
    }

    public final boolean hasEmail() {
        return !isEmpty(email);
    }

    public final AuthRequest setEmail(String email) {
        this.email = email;
        setArgument("username", email == null ? null : HTTPClient.urlEncode(email));

        return this;
    }

    public final boolean hasPassword() {
        return !isEmpty(password);
    }

    public final AuthRequest setPassword(String password) {
        this.password = password;
        setArgument("password", password == null ? null : HTTPClient.urlEncode(password));

        return this;
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

    public final AuthRequest setCaptchaSID(String sid) {
        captcha_sid = sid;
        setArgument("captcha_sid", captcha_sid);

        return this;
    }

    public final AuthRequest setCaptchaKey(String key) {
        captcha_key = key;
        setArgument("captcha_key", captcha_key);

        return this;
    }

    public final boolean isReadyToSend() {
        return hasArguments() && hasMethod() && hasEmail() && hasPassword();
    }

    public String toString() {
        return oauthUrl() + toStringWithoutDomain();
    }
}
