package ru.curoviyxru.j2vk.api.requests.notifications;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.notifications.NotificationsGetResponse;

/**
 *
 * @author curoviyxru
 */
public class NotificationsGet extends VKRequest {
    
    public NotificationsGet() {
        super(NotificationsGetResponse.class, "notifications.get");
    }

    public NotificationsGet setCount(int i) {
        setArgument("count", i == -1 ? null : String.valueOf(i));

        return this;
    }
    
    public NotificationsGet setStartFrom(String i) {
        setArgument("start_from", i);

        return this;
    }
}
