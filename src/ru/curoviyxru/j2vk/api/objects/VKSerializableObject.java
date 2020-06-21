package ru.curoviyxru.j2vk.api.objects;

import org.json.me.JSONObject;

public abstract class VKSerializableObject extends VKObject {

    public abstract VKSerializableObject deserialize(JSONObject json);

    public String toString() {
        return super.toString();
    }

    public JSONObject serialize() {
        return new JSONObject();
    }
}
