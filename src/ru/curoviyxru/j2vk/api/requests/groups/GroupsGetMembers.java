package ru.curoviyxru.j2vk.api.requests.groups;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.groups.GroupsGetMembersResponse;

/**
 *
 * @author curoviyxru
 */
public class GroupsGetMembers extends VKRequest {

    public GroupsGetMembers() {
        super(GroupsGetMembersResponse.class, "groups.getMembers");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
    }

    public GroupsGetMembers(long owner) {
        this();
        setGroupId(owner);
    }

    public GroupsGetMembers(long owner, int count, int offset) {
        this(owner);
        setCount(count);
        setOffset(offset);
    }

    public GroupsGetMembers(int count, int offset) {
        this();
        setCount(count);
        setOffset(offset);
    }

    public GroupsGetMembers setCount(int owner) {
        setArgument("count", owner);

        return this;
    }

    public GroupsGetMembers setOffset(int owner) {
        setArgument("offset", owner);

        return this;
    }

    public GroupsGetMembers setGroupId(long owner) {
        setArgument("group_id", Math.abs(owner));

        return this;
    }

    public GroupsGetMembers setSort(String s) {
        setArgument("sort", s);

        return this;
    }
    public static final String FRIENDS_FILTER = "friends", UNSURE_FILTER = "unsure", MANAGERS_FILTER = "managers",
            ID_ASC = "id_asc", ID_DESC = "id_desc", TIME_ASC = "time_asc", TIME_DESC = "time_desc";

    public GroupsGetMembers setFilter(String s) {
        setArgument("filter", s);

        return this;
    }
}
