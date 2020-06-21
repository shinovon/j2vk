package ru.curoviyxru.j2vk.api.requests.groups;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.groups.GroupsLeaveResponse;

/**
 *
 * @author curoviyxru
 */
public class GroupsLeave extends VKRequest {

    public GroupsLeave() {
        super(GroupsLeaveResponse.class, "groups.leave");
    }

    public GroupsLeave(long peer_id) {
        this();
        setGroupId(peer_id);
    }

    public GroupsLeave setGroupId(long id) {
        setArgument("group_id", Math.abs(id));

        return this;
    }
}
