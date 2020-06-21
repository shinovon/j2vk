package ru.curoviyxru.j2vk.api.requests.audio;

import ru.curoviyxru.j2vk.api.requests.VKAudioRequest;
import ru.curoviyxru.j2vk.api.responses.audio.AudioGetPlaylistsResponse;

/**
 *
 * @author curoviyxru
 */
public class AudioGetPlaylists extends VKAudioRequest {

    public long owner_id;

    public AudioGetPlaylists() {
        super(AudioGetPlaylistsResponse.class, "audio.getPlaylists");
        setArgument("extended", 1);
        setArgument("fields", extended_fields);
    }

    public AudioGetPlaylists(long owner) {
        this(owner, 50, 0);
    }

    public AudioGetPlaylists(long owner, int count, int offset) {
        this();
        setOwnerId(owner);
        setCount(count);
        setOffset(offset);
    }

    public AudioGetPlaylists(int count, int offset) {
        this(0, count, offset);
    }

    public AudioGetPlaylists setOwnerId(long i) {
        owner_id = i;
        if (hasOwnerId()) {
            setArgument("owner_id", owner_id);
        } else {
            setArgument("owner_id", null);
        }

        return this;
    }

    public AudioGetPlaylists setOffset(int i) {
        setArgument("offset", i == -1 ? null : String.valueOf(i));

        return this;
    }

    public AudioGetPlaylists setCount(int i) {
        setArgument("count", i == -1 ? null : String.valueOf(i));

        return this;
    }

    public boolean hasOwnerId() {
        return owner_id != 0;
    }
}
