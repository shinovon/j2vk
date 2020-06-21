package ru.curoviyxru.j2vk.auth;

import org.json.me.JSONException;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.VKConstants;
import ru.curoviyxru.j2vk.api.objects.Account;
import ru.curoviyxru.j2vk.api.requests.auth.AuthRequest;
import ru.curoviyxru.j2vk.api.requests.auth.AuthValidatePhone;
import ru.curoviyxru.j2vk.api.responses.auth.AuthResponse;
import ru.curoviyxru.j2vk.api.responses.auth.AuthValidatePhoneResponse;
import ru.curoviyxru.j2vk.HTTPClient;

/**
 *
 * @author curoviyxru
 */
public class Authorization {

    public static class AuthSignals {

        public static final int UNKNOWN_ERROR = 0;
        public static final int NEEDS_CAPTCHA = 1;
        public static final int NEEDS_VERIFICATION = 2;
        public static final int INVALID_PASSWORD = 3;
        public static final int WRONG_OTP = -2;
        public static final int WRONG_OTP_FORMAT = -1;
        public static final int SUCCESSFUL = 4;
        public static final int SPECIFIC_ERROR = -3;
    }

    public static AuthorizationResponse authorize(String email, String password, String captcha, String sid, String tfa, boolean sms) throws Exception {
        AuthRequest req = new AuthRequest(email, password).setCaptchaKey(captcha).setCaptchaSID(sid).set2FACode(tfa).setSMSForced(sms);
        AuthResponse response;
        String result = HTTPClient.makeResponseUnsafe(req);

        JSONObject obj;
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            VKConstants.debug(0, e);
            obj = null;
        }
        response = (AuthResponse) req.newEmptyResponse();

        if (obj == null || response == null) {
            response = null;
        } else {
            response = (AuthResponse) response.deserialize(obj);
        }

        int secs = 0;

        if (response == null) {
            return new AuthorizationResponse(AuthSignals.UNKNOWN_ERROR, response, secs, obj);
        }
        if (response.hasValidationSID() && sms) {
            AuthValidatePhoneResponse resp = (AuthValidatePhoneResponse) new AuthValidatePhone(response.validation_sid).execute();
            if (resp != null) {
                secs = resp.delay;
            }
            if (secs < 120) {
                secs = 120;
            }
        }
        if (response.isSuccessful()) {
            VKConstants.account = new Account(response.access_token);
            return new AuthorizationResponse(AuthSignals.SUCCESSFUL, response, secs, obj);
        } else {
            if (response.needs2FA()) {
                return new AuthorizationResponse(AuthSignals.NEEDS_VERIFICATION, response, secs, obj);
            } else if (response.needsCaptcha()) {
                return new AuthorizationResponse(AuthSignals.NEEDS_CAPTCHA, response, secs, obj);
            } else if (response.isAuthPairInvalid()) {
                return new AuthorizationResponse(AuthSignals.INVALID_PASSWORD, response, secs, obj);
            } else if (response.isOTPWrong()) {
                return new AuthorizationResponse(AuthSignals.WRONG_OTP, response, secs, obj);
            } else if (response.isOTPFormatIncorrect()) {
                return new AuthorizationResponse(AuthSignals.WRONG_OTP_FORMAT, response, secs, obj);
            } else if (response.isErrored()) {
                return new AuthorizationResponse(AuthSignals.SPECIFIC_ERROR, response, secs, obj);
            } else {
                return new AuthorizationResponse(AuthSignals.UNKNOWN_ERROR, response, secs, obj);
            }
        }
    }
}
