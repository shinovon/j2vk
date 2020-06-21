package ru.curoviyxru.j2vk.api.requests.messages;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesGetLongPollHistoryResponse;

/**
 *
 * @author curoviyxru
 */
public class MessagesGetLongPollHistory extends VKRequest {

    public MessagesGetLongPollHistory() {
        super(MessagesGetLongPollHistoryResponse.class, "messages.getLongPollHistory");
        setArgument("lp_version", 3);
        setEventsLimit(1000);
        setMsgsLimit(200);
        setArgument("credentials", 0);
        setArgument("onlines", 0);
    }

    public MessagesGetLongPollHistory(int ts) {
        this();
        setTS(ts);
    }

    public MessagesGetLongPollHistory setTS(int ts) {
        setArgument("ts", ts);

        return this;
    }

    public MessagesGetLongPollHistory setEventsLimit(int i) {
        setArgument("events_limit", i == -1 ? null : String.valueOf(i));

        return this;
    }

    public MessagesGetLongPollHistory setMsgsLimit(int i) {
        setArgument("msgs_limit", i == -1 ? null : String.valueOf(i));

        return this;
    }
}
