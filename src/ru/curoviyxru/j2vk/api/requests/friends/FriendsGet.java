package ru.curoviyxru.j2vk.api.requests.friends;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.friends.FriendsGetResponse;

/**
 *
 * @author curoviyxru
 */
public class FriendsGet extends VKRequest {

    public static final String HINTS = "hints";
    public static final String RANDOM = "random";
    public static final String NAME = "name";

    public FriendsGet() {
        super(FriendsGetResponse.class, "friends.get");
        setOrder("mobile");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
    }

    public FriendsGet(long owner) {
        this();
        setUserId(owner);
    }

    public FriendsGet(long owner, int count, int offset) {
        this(owner);
        setCount(count);
        setOffset(offset);
    }

    public FriendsGet(int count, int offset) {
        this();
        setCount(count);
        setOffset(offset);
    }

    public FriendsGet setCount(int count) {
        setArgument("count", count);

        return this;
    }

    public FriendsGet setOffset(int offset) {
        setArgument("offset", offset);

        return this;
    }

    public FriendsGet setOrder(String order) {
        setArgument("order", order);

        return this;
    }

    public FriendsGet setNameCase(String cases) {
        setArgument("name_case", cases);

        return this;
    }

    public FriendsGet setUserId(long owner) {
        setArgument("user_id", owner);

        return this;
    }
}
