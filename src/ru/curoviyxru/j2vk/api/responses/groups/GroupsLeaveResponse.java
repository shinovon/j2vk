package ru.curoviyxru.j2vk.api.responses.groups;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class GroupsLeaveResponse extends VKResponse {

    public boolean response;

    public VKSerializableObject deserialize(JSONObject o) {
        super.deserialize(o);
        if (o == null) {
            return this;
        }

        response = fromInteger(o.optInt("response"));

        return this;
    }
}
