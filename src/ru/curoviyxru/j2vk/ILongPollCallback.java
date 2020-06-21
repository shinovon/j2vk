package ru.curoviyxru.j2vk;

import org.json.me.JSONArray;

/**
 *
 * @author curoviyxru
 */
public interface ILongPollCallback {

    public void fetching();
    public void sleeping();
    public void update(JSONArray update);
}
