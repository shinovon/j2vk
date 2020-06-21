package ru.curoviyxru.j2vk.api.requests.newsfeed;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.newsfeed.NewsfeedGetResponse;

/**
 *
 * @author curoviyxru
 */
public class NewsfeedGet extends VKRequest {

    public NewsfeedGet() {
        super(NewsfeedGetResponse.class, "newsfeed.get");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
        setArgument("return_banned", 0);
        setArgument("max_photos", 4);
        setCount(20);
        setFilters("post");
    }

    public NewsfeedGet setCount(int count) {
        setArgument("count", count);

        return this;
    }

    public NewsfeedGet setFilters(String str) {
        setArgument("filters", str);

        return this;
    }

    public NewsfeedGet setStartTime(int time) {
        setArgument("start_time", time < 0 ? null : String.valueOf(time));

        return this;
    }

    public NewsfeedGet setStartFrom(String from) {
        setArgument("start_from", from);

        return this;
    }
}
