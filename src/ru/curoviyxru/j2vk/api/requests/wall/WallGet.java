package ru.curoviyxru.j2vk.api.requests.wall;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.wall.WallGetResponse;

/**
 *
 * @author curoviyxru
 */
public class WallGet extends VKRequest {

    public WallGet() {
        super(WallGetResponse.class, "wall.get");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
        setFilter(ALL);
    }
    public static final String ALL = "all", OWNER = "owner", OTHERS = "others";

    public WallGet(long owner) {
        this();
        setOwnerId(owner);
    }

    public WallGet(long owner, int count, int offset) {
        this(owner);
        setCount(count);
        setOffset(offset);
    }

    public WallGet setCount(int count) {
        setArgument("count", count);

        return this;
    }

    public WallGet setOffset(int offset) {
        setArgument("offset", offset);

        return this;
    }

    public WallGet setFilter(String fil) {
        setArgument("filter", fil);

        return this;
    }

    public WallGet setOwnerId(long owner) {
        setArgument("owner_id", owner);

        return this;
    }
}
