package ru.curoviyxru.j2vk.api.responses.groups;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.user.User;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class GroupsGetMembersResponse extends VKResponse {

    public User[] members;

    public boolean hasMembers() {
        return members != null && members.length > 0;
    }

    public VKSerializableObject deserialize(JSONObject o) {
        super.deserialize(o);
        if (o == null) {
            return this;
        }

        JSONObject json = o.optJSONObject("response");
        if (json != null) {
            JSONArray array = json.optJSONArray("items");
            if (array != null) {
                members = new User[array.length()];
                for (int i = 0; i < members.length; i++) {
                    JSONObject uObj = array.optJSONObject(i);
                    if (uObj != null) {
                        members[i] = (User) new User().deserialize(uObj);
                    }
                }
            }
        }

        return this;
    }
}
