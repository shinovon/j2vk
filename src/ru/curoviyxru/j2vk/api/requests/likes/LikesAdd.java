package ru.curoviyxru.j2vk.api.requests.likes;

import ru.curoviyxru.j2vk.api.objects.Likeable;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.likes.LikesAddResponse;

/**
 *
 * @author curoviyxru
 */
public class LikesAdd extends VKRequest {

    public LikesAdd() {
        super(LikesAddResponse.class, "likes.add");
    }

    public LikesAdd(Likeable item) {
        this();
        setItem(item);
    }

    public LikesAdd setItem(Likeable item) {
        setArgument("type", item.getLikeableType());
        setArgument("owner_id", item.getLikeableOwnerId());
        setArgument("item_id", item.getLikeableItemsId());
        return this;
    }
}
