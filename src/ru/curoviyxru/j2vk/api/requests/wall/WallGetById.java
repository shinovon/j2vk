package ru.curoviyxru.j2vk.api.requests.wall;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.wall.WallGetByIdResponse;

/**
 *
 * @author curoviyxru
 */
public class WallGetById extends VKRequest {

    //TODO: Сделать получение нескольких постов (а зачем?)
    public WallGetById() {
        super(WallGetByIdResponse.class, "wall.getById");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
    }

    public WallGetById(long owner, int id) {
        this();
        setPost(owner, id);
    }

    public WallGetById setPost(long owner, int id) {
        setArgument("posts", owner + "_" + id);

        return this;
    }
}
