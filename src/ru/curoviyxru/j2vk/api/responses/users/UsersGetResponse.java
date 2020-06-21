package ru.curoviyxru.j2vk.api.responses.users;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.user.User;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class UsersGetResponse extends VKResponse {

    public User[] users;

    public final boolean hasUsers() {
        return users != null && users.length > 0;
    }

    public final User getUser() {
        return hasUsers() ? users[0] : null;
    }

    public final boolean hasUser() {
        return getUser() != null;
    }

    public final VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONArray array = json.optJSONArray("response");
        if (array != null) {
            users = new User[array.length()];
            for (int i = 0; i < users.length; i++) {
                JSONObject obj = array.optJSONObject(i);
                if (obj != null) {
                    users[i] = (User) new User().deserialize(obj);
                }
            }
        }

        return this;
    }
}
