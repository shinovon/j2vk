package ru.curoviyxru.j2vk.auth;

import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.responses.auth.AuthResponse;

/**
 *
 * @author curoviyxru
 */
public class AuthorizationResponse {

    AuthResponse response;
    int signal;
    int timeout;
    JSONObject obj;
    
    public AuthorizationResponse(int status, AuthResponse response, int timeout, JSONObject obj) {
        signal = status;
        this.response = response;
        this.timeout = timeout;
        this.obj = obj;
    }

    public final int getSignal() {
        return signal;
    }

    public final AuthResponse getResponse() {
        return response;
    }

    public int getSMSTimeout() {
        return timeout;
    }
    
    public JSONObject getObject() {
        return obj;
    }
}
