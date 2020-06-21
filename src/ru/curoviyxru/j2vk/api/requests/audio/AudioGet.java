package ru.curoviyxru.j2vk.api.requests.audio;

import ru.curoviyxru.j2vk.api.requests.VKAudioRequest;
import ru.curoviyxru.j2vk.api.responses.audio.AudioGetResponse;

/**
 *
 * @author curoviyxru
 */
public class AudioGet extends VKAudioRequest {

    public long owner_id;

    public AudioGet() {
        super(AudioGetResponse.class, "audio.get");
    }

    public AudioGet(long owner) {
        this(owner, 100, 0);
    }

    public AudioGet(long owner, int count, int offset) {
        this();
        setOwnerId(owner);
        setCount(count);
        setOffset(offset);
    }

    public AudioGet(int count, int offset) {
        this(0, count, offset);
    }

    public AudioGet setOwnerId(long i) {
        owner_id = i;
        if (hasOwnerId()) {
            setArgument("owner_id", owner_id);
        } else {
            setArgument("owner_id", null);
        }

        return this;
    }

    public AudioGet setOffset(int i) {
        setArgument("offset", i == -1 ? null : String.valueOf(i));

        return this;
    }

    public AudioGet setCount(int i) {
        setArgument("count", i == -1 ? null : String.valueOf(i));

        return this;
    }

    public boolean hasOwnerId() {
        return owner_id != 0;
    }
}
