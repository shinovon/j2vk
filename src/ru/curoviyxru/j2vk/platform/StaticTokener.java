package ru.curoviyxru.j2vk.platform;

import ru.curoviyxru.j2vk.VKConstants;

/**
 *
 * @author curoviyxru
 */
public class StaticTokener implements ITokener {

    public String requestToken() {
        return VKConstants.staticRefreshToken;
    }
}
