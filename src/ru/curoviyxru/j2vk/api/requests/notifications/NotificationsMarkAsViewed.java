package ru.curoviyxru.j2vk.api.requests.notifications;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.notifications.NotificationsMarkAsViewedResponse;

/**
 *
 * @author curoviyxru
 */
public class NotificationsMarkAsViewed extends VKRequest {
    public NotificationsMarkAsViewed() {
        super(NotificationsMarkAsViewedResponse.class, "notifications.markAsViewed");
    }
}
