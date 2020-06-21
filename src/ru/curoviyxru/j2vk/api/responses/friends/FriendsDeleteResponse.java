package ru.curoviyxru.j2vk.api.responses.friends;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class FriendsDeleteResponse extends VKResponse {

    public int response;
    public int success;
    public static final int FRIEND_DELETED = 10, OUT_REQUEST_DELETED = 20, IN_REQUEST_DELETED = 30, SUGGESTION_DELETED = 40;

    public VKSerializableObject deserialize(JSONObject o) {
        super.deserialize(o);
        if (o == null) {
            return this;
        }

        JSONObject json = o.optJSONObject("response");
        if (json != null) {
            success = json.optInt("success");

            int type = json.optInt("friend_deleted");
            if (type == 1) {
                response = FriendsDeleteResponse.FRIEND_DELETED;
            } else {
                type = json.optInt("out_request_deleted");
                if (type == 1) {
                    response = FriendsDeleteResponse.OUT_REQUEST_DELETED;
                } else {
                    type = json.optInt("in_request_deleted");
                    if (type == 1) {
                        response = FriendsDeleteResponse.IN_REQUEST_DELETED;
                    } else {
                        type = json.optInt("suggestion_deleted");
                        if (type == 1) {
                            response = FriendsDeleteResponse.SUGGESTION_DELETED;
                        }
                    }
                }
            }
        }

        return this;
    }
}
