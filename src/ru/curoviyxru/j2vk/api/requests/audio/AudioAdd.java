package ru.curoviyxru.j2vk.api.requests.audio;

import ru.curoviyxru.j2vk.api.requests.VKAudioRequest;
import ru.curoviyxru.j2vk.api.responses.audio.AudioAddResponse;

/**
 *
 * @author curoviyxru
 */
public class AudioAdd extends VKAudioRequest {
    public AudioAdd(long owner, int id) {
        super(AudioAddResponse.class, "audio.add");
        setArgument("owner_id", owner);
        setArgument("audio_id", id);
    }
}
