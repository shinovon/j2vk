package ru.curoviyxru.j2vk.api.responses.auth;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class AuthResponse extends VKResponse {

    public String access_token, captcha_sid, captcha_img, validation_type, phone_mask, validation_sid;
    public long user_id;

    public final boolean needs2FA() {
        return hasPhoneMask() || hasValidationType() || hasValidationSID();
    }

    public final boolean hasValidationSID() {
        return !isEmpty(validation_sid);
    }

    public final boolean isAuthPairInvalid() {
        return hasErrorType() && error_type.equals("username_or_password_is_incorrect");
    }

    public final boolean isOTPFormatIncorrect() {
        return hasErrorType() && error_type.equals("otp_format_is_incorrect");
    }

    public final boolean isOTPWrong() {
        return hasErrorType() && error_type.equals("wrong_otp");
    }

    public final boolean needsCaptcha() {
        return hasCaptchaSID() || hasCaptchaImage();
    }

    public final boolean isSuccessful() {
        return super.isSuccessful() && isAuthorized();
    }

    public final boolean isAuthorized() {
        return hasAccessToken() && hasUserId();
    }

    public final boolean hasAccessToken() {
        return !isEmpty(access_token);
    }

    public final boolean hasCaptchaSID() {
        return !isEmpty(captcha_sid);
    }

    public final boolean hasCaptchaImage() {
        return !isEmpty(captcha_img); //TODO check for URL
    }

    public final boolean hasValidationType() {
        return !isEmpty(validation_type);
    }

    public final boolean hasPhoneMask() {
        return !isEmpty(phone_mask);
    }

    public final boolean hasUserId() {
        return user_id > 0;
    }

    public final VKSerializableObject deserialize(JSONObject obj) {
        super.deserialize(obj);
        if (obj == null) {
            return this;
        }

        access_token = obj.optString("access_token");
        //redirect_uri = obj.optString("redirect_uri");
        captcha_sid = obj.optString("captcha_sid");
        captcha_img = obj.optString("captcha_img");
        validation_type = obj.optString("validation_type");
        phone_mask = obj.optString("phone_mask");
        validation_sid = obj.optString("validation_sid");
        //expires_in = obj.optInt("expires_in", -1);
        user_id = obj.optLong("user_id");

        return this;
    }
}
