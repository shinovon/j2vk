package ru.curoviyxru.j2vk.api.requests.users;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.users.UsersGetFollowersResponse;

/**
 *
 * @author curoviyxru
 */
public class UsersGetFollowers extends VKRequest {

    public UsersGetFollowers() {
        super(UsersGetFollowersResponse.class, "users.getFollowers");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
    }

    public UsersGetFollowers(long owner) {
        this();
        setUserId(owner);
    }

    public UsersGetFollowers(long owner, int count, int offset) {
        this(owner);
        setCount(count);
        setOffset(offset);
    }

    public UsersGetFollowers(int count, int offset) {
        this();
        setCount(count);
        setOffset(offset);
    }
    
    public UsersGetFollowers setCount(int owner) {
        setArgument("count", owner);

        return this;
    }

    public UsersGetFollowers setOffset(int owner) {
        setArgument("offset", owner);

        return this;
    }

    public UsersGetFollowers setNameCase(String cases) {
        setArgument("name_case", cases);

        return this;
    }

    public UsersGetFollowers setUserId(long owner) {
        setArgument("user_id", owner);

        return this;
    }
}
