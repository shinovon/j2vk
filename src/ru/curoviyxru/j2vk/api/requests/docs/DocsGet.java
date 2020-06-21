package ru.curoviyxru.j2vk.api.requests.docs;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.docs.DocsGetResponse;

/**
 *
 * @author curoviyxru
 */
public class DocsGet extends VKRequest {
    
    public DocsGet(int count, int offset) {
        this(count, offset, 0);
    }
    
    public DocsGet(int count, int offset, int type) {
        super(DocsGetResponse.class, "docs.get");
        setArgument("offset", offset);
        setArgument("count", count);
        setArgument("type", type);
        setArgument("return_tags", 0);
    }
}
