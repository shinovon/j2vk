package ru.curoviyxru.j2vk.api.requests.friends;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.friends.FriendsAddResponse;

/**
 *
 * @author curoviyxru
 */
public class FriendsAdd extends VKRequest {

    //TODO: setText
    public FriendsAdd() {
        super(FriendsAddResponse.class, "friends.add");
        setArgument("follow", 0);
    }

    public FriendsAdd(long peer_id) {
        this();
        setUserId(peer_id);
    }

    public FriendsAdd setUserId(long id) {
        setArgument("user_id", id);

        return this;
    }
}
