package ru.curoviyxru.j2vk.api.requests;

/**
 *
 * @author curoviyxru
 */
public class VKAudioRequest extends VKRequest {

    public VKAudioRequest(Class responseClazz, String method) {
        super(responseClazz, method);
        setArgument("v", audio_api_version);
    }
}
