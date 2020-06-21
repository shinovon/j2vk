package ru.curoviyxru.j2vk;

import java.util.Timer;
import java.util.TimerTask;
import org.json.me.JSONArray;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.VKObject;
import ru.curoviyxru.j2vk.api.requests.messages.MessagesGetLongPollServer;
import ru.curoviyxru.j2vk.api.responses.messages.MessagesGetLongPollServerResponse;

/**
 *
 * @author curoviyxru
 */
public class LongPoll extends TimerTask {

    public static ILongPollCallback callback;
    public static Timer lpTimer;
    public static LongPoll task;
    public static int ts;
    public static int slept, limit = 3, sleptLimit = 17;
    public static boolean slowMode;
    public static boolean skipWait;
    String key, server;

    //fix recurrsive crashes and stops it
    public static void stop(boolean confirm) {
        try {
            if (lpTimer != null) {
                lpTimer.cancel();
            }
            if (callback != null) {
                callback.sleeping();
            }
        } catch (Exception expected) {
            //expected.printStackTrace();
        }
        //ts = 0;
        //key = server = null;
        try {
            if (!confirm && VKConstants.account != null) {
                start();
            }
        } catch (Exception expected) {
            VKConstants.debug(0, expected);
        }
    }

    public void stop() {
        stop(false);
    }

    public static void start() {
        (task = new LongPoll()).init();
    }

    public void init() {
        if (VKConstants.account == null) {
            return;
        }
        MessagesGetLongPollServerResponse resp = (MessagesGetLongPollServerResponse) new MessagesGetLongPollServer().execute();
        if (resp != null && resp.isSuccessful()) {
            ts = resp.ts;
            key = resp.key;
            server = resp.server;

            (lpTimer = new Timer()).schedule(this, 0);
        } else {
            stop();
        }
    }

    public void run() {
        while (true) {
            try {
                if (VKConstants.account == null) {
                    return;
                }

                slept = 0;
                skipWait = false;

                if (callback != null) {
                    callback.fetching();
                }

                String url = (server.startsWith("http") ? "" : (VKConstants.apiUrl().startsWith("http:") ? "http://" : "https://")) + server + "?act=a_check&key=" + key + "&ts=" + ts + "&wait=2&mode=202&version=3"; //or wait=1?
                String response = null;
                try {
                    response = HTTPClient.getURLString(url);
                } catch (Exception e) {
                    VKConstants.debug(0, e);
                }
                if (!VKObject.isEmpty(response)) {
                    JSONArray updates = null;
                    try {
                        JSONObject json = new JSONObject(response);

                        int failed = json.optInt("failed");
                        if (failed == 2 || failed == 3 || failed == 4) {
                            stop();
                            return;
                        }

                        int ts1 = json.optInt("ts");
                        if (ts1 != 0) {
                            ts = ts1;
                        }

                        updates = json.optJSONArray("updates");
                    } catch (Exception e) {
                        VKConstants.debug(0, e);
                        try {
                            updates = new JSONArray(response);
                        } catch (Exception ex) {
                            VKConstants.debug(0, ex);
                        }
                    }

                    try {
                        parse(updates);
                    } catch (Exception e) {
                        VKConstants.debug(0, e);
                    }
                }

                if (callback != null) {
                    callback.sleeping();
                }

                while ((slowMode && slept < sleptLimit) || (!slowMode && slept < limit)) {
                    if (skipWait) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                    }
                    ++slept;
                }
            } catch (Exception e) {
                VKConstants.debug(0, e);
                //stop();
                //return;
            }
        }
    }

    public void parse(JSONArray updates) {
        if (updates == null || callback == null) {
            return;
        }

        for (int i = 0; i < updates.length(); i++) {
            JSONArray update = updates.optJSONArray(i);

            if (update != null) {
                try {
                    callback.update(update);
                } catch (Exception e) {
                }
            }
        }
    }
}
