package ru.curoviyxru.j2vk.api.requests.account;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.account.AccountSetOfflineResponse;

/**
 *
 * @author curoviyxru
 */
public class AccountSetOffline extends VKRequest {
    public AccountSetOffline() {
        super(AccountSetOfflineResponse.class, "account.setOffline");
    }
}
