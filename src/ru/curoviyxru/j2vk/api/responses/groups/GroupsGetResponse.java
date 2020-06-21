package ru.curoviyxru.j2vk.api.responses.groups;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.user.Group;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class GroupsGetResponse extends VKResponse {

    public Group[] groups;

    public final boolean hasGroups() {
        return groups != null && groups.length > 0;
    }

    public final Group getGroup() {
        return hasGroups() ? groups[0] : null;
    }

    public final boolean hasGroup() {
        return getGroup() != null;
    }

    public final VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject obj = json.optJSONObject("response");
        JSONArray array = obj != null ? obj.optJSONArray("items") : null;
        if (array != null) {
            groups = new Group[array.length()];
            for (int i = 0; i < groups.length; i++) {
                JSONObject obj1 = array.optJSONObject(i);
                if (obj1 != null) {
                    groups[i] = (Group) new Group().deserialize(obj1);
                }
            }
        }

        return this;
    }
}
