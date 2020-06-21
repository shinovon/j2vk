package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesSetActivityResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesSetActivity extends VKRequest {

    public static final String TYPING = "typing", AUDIO_MESSAGE = "audiomessage";

    public MessagesSetActivity() {
        super(MessagesSetActivityResponse.class, "messages.setActivity");
    }

    public MessagesSetActivity(long peer, String type) {
        this();

        setType(type);
        setPeerId(peer);
    }

    public MessagesSetActivity setPeerId(long peer_id) {
        setArgument("peer_id", peer_id);

        return this;
    }

    public MessagesSetActivity setType(String type) {
        setArgument("type", type);

        return this;
    }
}
