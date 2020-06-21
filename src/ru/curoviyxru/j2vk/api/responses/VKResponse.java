package ru.curoviyxru.j2vk.api.responses;

import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

public class VKResponse extends VKSerializableObject {

    public String error, error_description, error_type, error_msg;
    public int error_code;

    public boolean isSuccessful() {
        return !hasError() && !hasErrorType() && !hasErrorDescription() && !hasErrorCode() && !hasErrorMessage();
    }

    public final String getErrorMessage() {
        return error_msg;
    }

    public final boolean hasErrorMessage() {
        return !isEmpty(error_msg);
    }

    public final boolean hasErrorDescription() {
        return !isEmpty(error_description);
    }

    public final boolean hasErrorCode() {
        return error_code != 0;
    }

    public final boolean hasError() {
        return !isEmpty(error);
    }

    public final boolean hasErrorType() {
        return !isEmpty(error_type);
    }

    public final boolean isErrored() {
        return !isSuccessful();
    }

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        if (json.has("error")) {
            JSONObject obj = json.optJSONObject("error");
            if (obj != null) {
                error_code = obj.optInt("error_code");
                error_msg = obj.optString("error_msg");
            } else {
                error = json.optString("error");
            }
        } else {
            error_code = json.optInt("error_code");
            error_msg = json.optString("error_msg");
        }

        error_description = json.optString("error_description");
        error_type = json.optString("error_type");

        return this;
    }
}
