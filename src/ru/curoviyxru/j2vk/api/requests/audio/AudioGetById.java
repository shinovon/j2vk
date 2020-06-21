package ru.curoviyxru.j2vk.api.requests.audio;

import ru.curoviyxru.j2vk.api.requests.VKAudioRequest;
import ru.curoviyxru.j2vk.api.responses.audio.AudioGetByIdResponse;

/**
 *
 * @author curoviyxru
 */
public class AudioGetById extends VKAudioRequest {

    public AudioGetById() {
        super(AudioGetByIdResponse.class, "audio.getById");
    }

    public AudioGetById(String id) {
        this();
        setId(id);
    }

    public AudioGetById setId(String id) {
        setArgument("audios", id);

        return this;
    }
}
