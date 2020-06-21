package ru.curoviyxru.j2vk.api.requests.wall;

import ru.curoviyxru.j2vk.api.objects.attachments.Post;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.wall.WallGetCommentsResponse;

/**
 *
 * @author curoviyxru
 */
public class WallGetComments extends VKRequest {

    public WallGetComments() {
        super(WallGetCommentsResponse.class, "wall.getComments");
        setArgument("fields", extended_fields);
        setArgument("extended", 1);
        setArgument("thread_items_count", 10);
        setArgument("need_likes", 1);
        setSort("asc");
    }
    
    public WallGetComments(int count, int offset) {
        this();
        
        setCount(count);
        setOffset(offset);
    }
    
    public WallGetComments setSort(String sort) {
        setArgument("sort", sort);
        return this;
    }

    public WallGetComments setCommentId(int cid) {
        setArgument("comment_id", cid);
        return this;
    }

    public WallGetComments setPost(Post post) {
        setArgument("owner_id", post.getLikeableOwnerId());
        setArgument("post_id", post.getLikeableItemsId());
        return this;
    }

    public WallGetComments setCount(int count) {
        setArgument("count", count);
        return this;
    }

    public WallGetComments setOffset(int offset) {
        setArgument("offset", offset);
        return this;
    }

    public WallGetComments setStartCommentId(int intValue) {
        setArgument("start_comment_id", intValue);
        return this;
    }
}
