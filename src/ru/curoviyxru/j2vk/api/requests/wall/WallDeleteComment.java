package ru.curoviyxru.j2vk.api.requests.wall;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.wall.WallDeleteCommentResponse;

/**
 *
 * @author curoviyxru
 */
public class WallDeleteComment extends VKRequest {

    public WallDeleteComment() {
        super(WallDeleteCommentResponse.class, "wall.deleteComment");
    }

    public WallDeleteComment(long owner, int id) {
        this();
        setComment(owner, id);
    }

    public WallDeleteComment setComment(long owner, int id) {
        setArgument("owner_id", owner);
        setArgument("comment_id", id);

        return this;
    }
}