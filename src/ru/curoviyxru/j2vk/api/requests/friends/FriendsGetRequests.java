package ru.curoviyxru.j2vk.api.requests.friends;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.friends.FriendsGetRequestsResponse;

/**
 *
 * @author curoviyxru
 */
public class FriendsGetRequests extends VKRequest {
    public FriendsGetRequests(int count, int offset, boolean out) {
        super(FriendsGetRequestsResponse.class, "friends.getRequests");
        setArgument("offset", offset);
        setArgument("count", count);
        setArgument("out", fromBoolean(out));
        setArgument("extended", 1);
        setArgument("need_viewed", 1);
        setArgument("suggested", 0);
        setArgument("need_mutual", 0);
        setArgument("fields", extended_fields);
    }
}
