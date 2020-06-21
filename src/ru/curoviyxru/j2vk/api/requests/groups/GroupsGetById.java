package ru.curoviyxru.j2vk.api.requests.groups;

import java.util.Vector;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.groups.GroupsGetByIdResponse;

public class GroupsGetById extends VKRequest {

    Vector ids = new Vector();

    public GroupsGetById() {
        super(GroupsGetByIdResponse.class, "groups.getById");
        setArgument("extended", 1);
        setArgument("fields", fields);
    }

    public GroupsGetById(long id) {
        this();
        addGroupId(id);
    }

    public GroupsGetById addGroupId(long id) {
        if (id < 0) {
            id = id * -1;
        }
        if (ids.indexOf(new Long(id)) != -1) {
            return this;
        }

        ids.addElement(new Long(id));
        updateIds();

        return this;
    }

    public GroupsGetById removeGroupId(long id) {
        if (id < 0) {
            id = id * -1;
        }
        if (ids.indexOf(new Long(id)) == -1) {
            return this;
        }

        ids.removeElement(new Long(id));
        updateIds();

        return this;
    }

    private void updateIds() {
        if (hasGroupIds()) {
            setArgument("group_ids", join(",", ids));
        } else {
            setArgument("group_ids", null);
        }
    }

    public boolean hasGroupIds() {
        return ids != null && ids.size() > 0;
    }

    public Vector getGroupIds() {
        return ids;
    }
}
