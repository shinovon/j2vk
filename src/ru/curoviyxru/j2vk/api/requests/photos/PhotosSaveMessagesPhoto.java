package ru.curoviyxru.j2vk.api.requests.photos;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.photos.PhotosSaveMessagesPhotoResponse;
import ru.curoviyxru.j2vk.HTTPClient;

/**
 *
 * @author curoviyxru
 */
public class PhotosSaveMessagesPhoto extends VKRequest {

    public PhotosSaveMessagesPhoto() {
        super(PhotosSaveMessagesPhotoResponse.class, "photos.saveMessagesPhoto");
    }

    public PhotosSaveMessagesPhoto(String photo, long server, String hash) {
        this();

        setParams(photo, server, hash);
    }

    public PhotosSaveMessagesPhoto setParams(String photo, long server, String hash) {
        setArgument("photo", photo != null ? HTTPClient.urlEncode(photo) : null);
        setArgument("server", server);
        setArgument("hash", hash);

        return this;
    }
}
