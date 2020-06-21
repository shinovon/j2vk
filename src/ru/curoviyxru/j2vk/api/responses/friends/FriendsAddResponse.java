package ru.curoviyxru.j2vk.api.responses.friends;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class FriendsAddResponse extends VKResponse {

    public int response;
    public static final int SENT = 1, ACCEPTED = 2, REPEATED = 4;

    public VKSerializableObject deserialize(JSONObject o) {
        super.deserialize(o);
        if (o == null) {
            return this;
        }

        response = o.optInt("response", -1);

        return this;
    }
}
