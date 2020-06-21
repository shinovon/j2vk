package ru.curoviyxru.j2vk.api.objects;

import ru.curoviyxru.j2vk.VKConstants;

import ru.curoviyxru.j2vk.api.requests.auth.AuthRefreshToken;
import ru.curoviyxru.j2vk.api.requests.users.UsersGet;
import ru.curoviyxru.j2vk.api.responses.auth.AuthRefreshTokenResponse;
import ru.curoviyxru.j2vk.api.responses.auth.AuthResponse;
import ru.curoviyxru.j2vk.api.responses.users.UsersGetResponse;
import ru.curoviyxru.j2vk.HTTPClient;
import ru.curoviyxru.j2vk.api.requests.account.AccountSetOffline;
import ru.curoviyxru.j2vk.api.responses.account.AccountSetOfflineResponse;

/**
 *
 * @author curoviyxru
 */
public class Account extends VKObject {

    public String token;
    public boolean refreshed;
    public long id;

    public long getId() {
        return id;
    }

    public Account(String token) throws Exception {
        setToken(token);

        try {
            UsersGetResponse get = (UsersGetResponse) new UsersGet().execute(this, false);
            if (get != null && get.hasUser() && get.getUser().id != 0) {
                setId(get.getUser().id);
                //refreshToken();
            } else {
                throw new Exception("No user_id.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Account setIsRefreshed(boolean r) {
        refreshed = r;

        return this;
    }

    public Account setToken(String token) {
        this.token = token;

        return this;
    }

    public Account setId(long id) {
        this.id = id;

        return this;
    }

    public boolean isRefreshed() {
        return refreshed;
    }

    public String getToken() {
        return token;
    }

    public boolean hasToken() {
        return !isEmpty(token);
    }

    public Account refreshToken() {
        String gcm = HTTPClient.requestToken();
        if (!isEmpty(gcm)) {
            AuthRefreshTokenResponse r = (AuthRefreshTokenResponse) new AuthRefreshToken(gcm).execute(this, false);
            if (r != null && r.hasToken()) {
                setToken(r.token);
                setIsRefreshed(true);
                VKConstants.client.saveConfig();
            }
        }

        return this;
    }

    public Account(AuthResponse auth) {
        if (auth != null && auth.isSuccessful()) {
            setToken(auth.access_token);
            setId(auth.user_id);
        } else {
            throw new IllegalArgumentException("AuthReponse is invalid.");
        }
    }

    public boolean setOffline() {
        if (!hasToken())
            return false;
         
        try {
            AccountSetOfflineResponse resp = (AccountSetOfflineResponse) new AccountSetOffline().execute();
            return resp.isSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
