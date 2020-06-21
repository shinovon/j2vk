package ru.curoviyxru.j2vk.api.responses;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.objects.user.Group;
import ru.curoviyxru.j2vk.api.objects.user.User;

public class VKExtendedResponse extends VKResponse {

    public User[] profiles;
    public Group[] groups;
    //public Conversation[] conversations;

    public VKSerializableObject deserialize(JSONObject json1) {
        super.deserialize(json1);
        if (json1 == null) {
            return this;
        }

        JSONObject json = json1.optJSONObject("response");
        if (json != null) {
            JSONArray array = json.optJSONArray("groups");
            if (array != null) {
                groups = new Group[array.length()];
                for (int i = 0; i < groups.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        groups[i] = (Group) new Group().deserialize(obj1);
                    }
                }
            }
            array = json.optJSONArray("profiles");
            if (array != null) {
                profiles = new User[array.length()];
                for (int i = 0; i < profiles.length; i++) {
                    JSONObject obj1 = array.optJSONObject(i);
                    if (obj1 != null) {
                        profiles[i] = (User) new User().deserialize(obj1);
                    }
                }
            }
            /* array = json.optJSONArray("conversations");
             if (array != null) {
             conversations = new Conversation[array.length()];
             for (int i = 0; i < conversations.length; i++) {
             JSONObject obj1 = array.optJSONObject(i);
             if (obj1 != null) conversations[i] = (Conversation) new Conversation().deserialize(obj1);
             }
             } */
        }

        return this;
    }

    /* public boolean hasConversations() {
     return conversations != null && conversations.length > 0;
     } */
    public boolean hasProfiles() {
        return profiles != null && profiles.length > 0;
    }

    public boolean hasGroups() {
        return groups != null && groups.length > 0;
    }
}
