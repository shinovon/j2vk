package ru.curoviyxru.j2vk.api.requests.photos;

import ru.curoviyxru.j2vk.api.requests.VKAudioRequest;
import ru.curoviyxru.j2vk.api.responses.photos.PhotosGetResponse;

/**
 *
 * @author curoviyxru
 */
public class PhotosGet extends VKAudioRequest {
    public PhotosGet(long owner, int id, int offset, int count) {
        super(PhotosGetResponse.class, "photos.get");
        setArgument("owner_id", owner);
        setArgument("album_id", id);
        setArgument("photo_sizes", 1);
        setArgument("count", count);
        setArgument("offset", offset);
    }
}