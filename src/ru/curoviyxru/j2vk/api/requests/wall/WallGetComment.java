package ru.curoviyxru.j2vk.api.requests.wall;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.wall.WallGetCommentResponse;

/**
 *
 * @author curoviyxru
 */
public class WallGetComment extends VKRequest {

    public WallGetComment() {
        super(WallGetCommentResponse.class, "wall.getComment");
    }

    public WallGetComment(long owner, int id) {
        this();
        setComment(owner, id);
    }

    public WallGetComment setComment(long owner, int id) {
        setArgument("owner_id", owner);
        setArgument("comment_id", id);

        return this;
    }
}