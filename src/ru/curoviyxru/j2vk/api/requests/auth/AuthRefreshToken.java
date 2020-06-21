package ru.curoviyxru.j2vk.api.requests.auth;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.auth.AuthRefreshTokenResponse;

/**
 *
 * @author curoviyxru
 */
public class AuthRefreshToken extends VKRequest {

    public AuthRefreshToken() {
        super(AuthRefreshTokenResponse.class, "auth.refreshToken");
        setArgument("v", audio_api_version);
    }

    public AuthRefreshToken(String receipt) {
        this();
        setReceipt(receipt);
    }

    public AuthRefreshToken setReceipt(String receipt) {
        setArgument("receipt", receipt);

        return this;
    }
}
