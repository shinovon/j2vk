package ru.curoviyxru.j2vk.api.requests.photos;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.photos.PhotosGetMessagesUploadServerResponse;

/**
 *
 * @author curoviyxru
 */
public class PhotosGetMessagesUploadServer extends VKRequest {

    public PhotosGetMessagesUploadServer() {
        super(PhotosGetMessagesUploadServerResponse.class, "photos.getMessagesUploadServer");
    }

    public PhotosGetMessagesUploadServer(long pid) {
        this();
        setPeerId(pid);
    }

    public PhotosGetMessagesUploadServer setPeerId(long pid) {
        setArgument("peer_id", pid);

        return this;
    }
}
