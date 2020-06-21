package ru.curoviyxru.j2vk.api.requests.photos;

import ru.curoviyxru.j2vk.api.requests.VKAudioRequest;
import ru.curoviyxru.j2vk.api.responses.photos.PhotosGetAlbumsResponse;

/**
 *
 * @author curoviyxru
 */
public class PhotosGetAlbums extends VKAudioRequest {
    public PhotosGetAlbums(long owner, int offset, int count) {
        super(PhotosGetAlbumsResponse.class, "photos.getAlbums");
        setArgument("owner_id", owner);
        setArgument("photo_sizes", 1);
        setArgument("need_system", 1);
        setArgument("need_covers", 1);
        setArgument("count", count);
        setArgument("offset", offset);
    }
}