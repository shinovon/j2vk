package ru.curoviyxru.j2vk.api.requests.docs;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.docs.DocsGetMessagesUploadServerResponse;

/**
 *
 * @author curoviyxru
 */
public class DocsGetMessagesUploadServer extends VKRequest {

    public static final String DOC = "doc", AUDIO_MESSAGE = "audio_message", GRAFFITI = "graffiti";

    public DocsGetMessagesUploadServer(long pid) {
        this(pid, DOC);
    }

    public DocsGetMessagesUploadServer(long pid, String type) {
        super(DocsGetMessagesUploadServerResponse.class, "docs.getMessagesUploadServer");
        setType(type);
        setPeerId(pid);
    }

    public DocsGetMessagesUploadServer setType(String type) {
        setArgument("type", type);

        return this;
    }

    public DocsGetMessagesUploadServer setPeerId(long peer_id) {
        setArgument("peer_id", peer_id != 0 ? String.valueOf(peer_id) : null);

        return this;
    }
}
