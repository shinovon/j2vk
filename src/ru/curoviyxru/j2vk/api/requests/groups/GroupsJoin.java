package ru.curoviyxru.j2vk.api.requests.groups;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.groups.GroupsJoinResponse;

/**
 *
 * @author curoviyxru
 */
public class GroupsJoin extends VKRequest {

    public GroupsJoin() {
        super(GroupsJoinResponse.class, "groups.join");
    }

    public GroupsJoin(long peer_id) {
        this();
        setGroupId(peer_id);
    }

    public GroupsJoin setGroupId(long id) {
        setArgument("group_id", Math.abs(id));

        return this;
    }
}
