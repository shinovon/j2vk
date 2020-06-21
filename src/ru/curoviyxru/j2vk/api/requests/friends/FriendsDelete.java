package ru.curoviyxru.j2vk.api.requests.friends;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.friends.FriendsDeleteResponse;

/**
 *
 * @author curoviyxru
 */
public class FriendsDelete extends VKRequest {

    public FriendsDelete() {
        super(FriendsDeleteResponse.class, "friends.delete");
    }

    public FriendsDelete(long peer_id) {
        this();
        setUserId(peer_id);
    }

    public FriendsDelete setUserId(long id) {
        setArgument("user_id", id);

        return this;
    }
}
