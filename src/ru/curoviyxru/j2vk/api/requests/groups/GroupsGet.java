package ru.curoviyxru.j2vk.api.requests.groups;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.groups.GroupsGetResponse;

/**
 *
 * @author curoviyxru
 */
public class GroupsGet extends VKRequest {

    public GroupsGet() {
        super(GroupsGetResponse.class, "groups.get");
        setArgument("extended", 1);
        setArgument("fields", fields); //because most of fields are for users.
    }

    public GroupsGet(long owner) {
        this();
        setUserId(owner);
    }

    public GroupsGet(long owner, int count, int offset) {
        this(owner);
        setCount(count);
        setOffset(offset);
    }

    public GroupsGet(int count, int offset) {
        this();
        setCount(count);
        setOffset(offset);
    }

    public GroupsGet setCount(int owner) {
        setArgument("count", owner);

        return this;
    }

    public GroupsGet setOffset(int owner) {
        setArgument("offset", owner);

        return this;
    }

    public GroupsGet setFilter(String fil) {
        setArgument("filter", fil);

        return this;
    }

    public GroupsGet setNameCase(String cases) {
        setArgument("name_case", cases);

        return this;
    }

    public GroupsGet setUserId(long owner) {
        setArgument("user_id", owner);

        return this;
    }
}
