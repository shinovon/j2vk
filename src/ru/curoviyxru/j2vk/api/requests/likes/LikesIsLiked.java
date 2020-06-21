package ru.curoviyxru.j2vk.api.requests.likes;

import ru.curoviyxru.j2vk.api.objects.Likeable;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.likes.LikesIsLikedResponse;

/**
 *
 * @author curoviyxru
 */
public class LikesIsLiked extends VKRequest {

    public LikesIsLiked() {
        super(LikesIsLikedResponse.class, "likes.isLiked");
    }

    public LikesIsLiked(long uid, Likeable item) {
        this();
        setUserId(uid);
        setItem(item);
    }

    public LikesIsLiked setUserId(long id) {
        setArgument("user_id", id + "");
        return this;
    }

    public LikesIsLiked setItem(Likeable item) {
        setArgument("type", item.getLikeableType());
        setArgument("owner_id", item.getLikeableOwnerId());
        setArgument("item_id", item.getLikeableItemsId());
        return this;
    }
}
