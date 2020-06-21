package ru.curoviyxru.j2vk.api.requests.wall;

import ru.curoviyxru.j2vk.api.objects.Likeable;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.wall.WallRepostResponse;
import ru.curoviyxru.j2vk.HTTPClient;

/**
 *
 * @author curoviyxru
 */
public class WallRepost extends VKRequest {

    public WallRepost() {
        super(WallRepostResponse.class, "wall.repost");
    }

    public WallRepost setItem(Likeable item) {
        setArgument("object", item == null ? null : item.toString());
        return this;
    }

    public WallRepost setMessage(String msg) {
        setArgument("message", !isEmpty(msg) ? HTTPClient.urlEncode(msg) : null);
        return this;
    }

    public WallRepost setGroupId(int id) {
        setArgument("group_id", Math.abs(id));
        return this;
    }
}
