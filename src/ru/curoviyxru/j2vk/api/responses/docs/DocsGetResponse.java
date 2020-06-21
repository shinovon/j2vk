package ru.curoviyxru.j2vk.api.responses.docs;

import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.attachments.Document;
import ru.curoviyxru.j2vk.api.responses.VKResponse;

/**
 *
 * @author curoviyxru
 */
public class DocsGetResponse extends VKResponse {

    public Document[] docs;

    public boolean hasDocs() {
        return docs != null && docs.length > 0;
    }
    
    public final VKSerializableObject deserialize(JSONObject json) {
        super.deserialize(json);
        if (json == null) {
            return this;
        }

        JSONObject obj = json.optJSONObject("response");
        JSONArray array = obj != null ? obj.optJSONArray("items") : null;
        if (array != null) {
            docs = new Document[array.length()];
            for (int i = 0; i < docs.length; i++) {
                JSONObject obj1 = array.optJSONObject(i);
                if (obj1 != null) {
                    docs[i] = (Document) new Document().deserialize(obj1);
                }
            }
        }

        return this;
    }
}
