package ru.curoviyxru.j2vk.api.requests.execute;

import ru.curoviyxru.j2vk.api.objects.attachments.AudioPlaylist;
import ru.curoviyxru.j2vk.api.requests.VKAudioRequest;
import ru.curoviyxru.j2vk.api.responses.execute.ExecuteGetPlaylistResponse;

/**
 *
 * @author curoviyxru
 */
public class ExecuteGetPlaylist extends VKAudioRequest {

    public ExecuteGetPlaylist(long owner_id, int id, String access_key) {
        super(ExecuteGetPlaylistResponse.class, "execute.getPlaylist");
        setArgument("owner_id", owner_id);
        setArgument("id", id);
        setArgument("access_key", access_key);
    }

    public ExecuteGetPlaylist(AudioPlaylist p) {
        this(p.owner_id, p.id, p.access_key);
    }
}
