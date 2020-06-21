package ru.curoviyxru.j2vk.api.requests.docs;

import ru.curoviyxru.j2vk.HTTPClient;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.docs.DocsSaveResponse;

/**
 *
 * @author curoviyxru
 */
public class DocsSave extends VKRequest {

    public DocsSave() {
        super(DocsSaveResponse.class, "docs.save");
    }

    public DocsSave(String file) {
        this();

        setFile(file);
    }

    public DocsSave setFile(String file) {
        setArgument("file", file != null ? HTTPClient.urlEncode(file) : null);

        return this;
    }
}
