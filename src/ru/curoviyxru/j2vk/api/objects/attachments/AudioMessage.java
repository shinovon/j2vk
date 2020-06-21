package ru.curoviyxru.j2vk.api.objects.attachments;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;

/**
 *
 * @author curoviyxru
 */
public class AudioMessage extends Attachment {

    public int id;
    public long owner_id;
    public int duration;
    public int[] waveform;
    public String link_ogg, link_mp3;
    public int minWaveform = Integer.MAX_VALUE, maxWaveform = Integer.MIN_VALUE;

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        id = json.optInt("id");
        owner_id = json.optLong("owner_id");
        duration = json.optInt("duration");
        link_ogg = json.optString("link_ogg");
        link_mp3 = json.optString("link_mp3");
        JSONArray array = json.optJSONArray("waveform");
        if (array != null) {
            waveform = new int[array.length()];
            for (int i = 0; i < waveform.length; i++) {
                waveform[i] = array.optInt(i);
                minWaveform = Math.min(minWaveform, waveform[i]);
                maxWaveform = Math.max(maxWaveform, waveform[i]);
            }
        }

        return this;
    }

    public boolean hasMP3() {
        return !isEmpty(link_mp3);
    }
    
    public String toString() {
        return "audio_message" + owner_id + "_" + id;
    }
}
