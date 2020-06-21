package ru.curoviyxru.j2vk.api.requests.photos;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.photos.PhotosGetByIdResponse;

/**
 *
 * @author curoviyxru
 */
public class PhotosGetById extends VKRequest {

    public PhotosGetById() {
        super(PhotosGetByIdResponse.class, "photos.getById");
        setArgument("photo_sizes", fromBoolean(true));
    }

    public PhotosGetById(long owner_id, int id) {
        this();
        setPhoto(owner_id, id);
    }

    public PhotosGetById setPhoto(long owner_id, int id) {
        setArgument("photos", owner_id + "_" + id);

        return this;
    }
}
