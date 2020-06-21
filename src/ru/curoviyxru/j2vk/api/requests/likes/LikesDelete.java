package ru.curoviyxru.j2vk.api.requests.likes;

import ru.curoviyxru.j2vk.api.objects.Likeable;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.likes.LikesDeleteResponse;

/**
 *
 * @author curoviyxru
 */
public class LikesDelete extends VKRequest {

    public LikesDelete() {
        super(LikesDeleteResponse.class, "likes.delete");
    }

    public LikesDelete(Likeable item) {
        this();
        setItem(item);
    }

    public LikesDelete setItem(Likeable item) {
        setArgument("type", item.getLikeableType());
        setArgument("owner_id", item.getLikeableOwnerId());
        setArgument("item_id", item.getLikeableItemsId());
        return this;
    }
}