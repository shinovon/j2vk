package ru.curoviyxru.j2vk;

import java.util.Hashtable;

import ru.curoviyxru.j2vk.api.objects.user.Page;
import ru.curoviyxru.j2vk.api.requests.groups.GroupsGetById;
import ru.curoviyxru.j2vk.api.requests.users.UsersGet;
import ru.curoviyxru.j2vk.api.responses.VKExtendedResponse;
import ru.curoviyxru.j2vk.api.responses.VKResponse;
import ru.curoviyxru.j2vk.api.responses.friends.FriendsGetResponse;
import ru.curoviyxru.j2vk.api.responses.groups.GroupsGetByIdResponse;
import ru.curoviyxru.j2vk.api.responses.groups.GroupsGetMembersResponse;
import ru.curoviyxru.j2vk.api.responses.groups.GroupsGetResponse;
import ru.curoviyxru.j2vk.api.responses.users.UsersGetFollowersResponse;
import ru.curoviyxru.j2vk.api.responses.users.UsersGetResponse;

/**
 *
 * @author curoviyxru
 */
public class PageStorage {
    
    //TODO: Get rid of it.
    static Hashtable table = new Hashtable();

    public static Page get(long id) {
        if (id == 0) id = VKConstants.account.getId();
        
        if (!table.containsKey(String.valueOf(id))) {
            load(id);
        }
        return (Page) table.get(String.valueOf(id));
    }

    public static void load(long id) {
        load(id, VKConstants.extended_fields);
    }
    
    public static void load(long id, String fields) {
        if (VKConstants.account != null) {
            if (id == 0) id = VKConstants.account.getId();
            
            if (id > 0) {
                UsersGetResponse resp = (UsersGetResponse) new UsersGet(id).setArgument("fields", fields).execute();
                if (resp != null && resp.hasUser()) {
                    put(resp.getUser());
                }
            } else if (id != 0) {
                GroupsGetByIdResponse resp = (GroupsGetByIdResponse) new GroupsGetById(id).setArgument("fields", fields).execute();
                if (resp != null && resp.hasGroup()) {
                    put(resp.getGroup());
                }
            }
        }
    }

    public static void parse(VKResponse resp) {
        if (resp == null) {
            return;
        }
        if (resp instanceof VKExtendedResponse) {
            VKExtendedResponse r = (VKExtendedResponse) resp;
            if (r.hasProfiles()) {
                for (int i = 0; i < r.profiles.length; i++) {
                    Page u = r.profiles[i];
                    if (u != null) {
                        put(u);
                    }
                }
            }
            if (r.hasGroups()) {
                for (int i = 0; i < r.groups.length; i++) {
                    Page u = r.groups[i];
                    if (u != null) {
                        put(u);
                    }
                }
            }
        } else if (resp instanceof UsersGetResponse) {
            UsersGetResponse r = (UsersGetResponse) resp;
            if (r.hasUsers()) {
                for (int i = 0; i < r.users.length; i++) {
                    Page u = r.users[i];
                    if (u != null) {
                        put(u);
                    }
                }
            }
        } else if (resp instanceof GroupsGetByIdResponse) {
            GroupsGetByIdResponse r = (GroupsGetByIdResponse) resp;
            if (r.hasGroups()) {
                for (int i = 0; i < r.groups.length; i++) {
                    Page u = r.groups[i];
                    if (u != null) {
                        put(u);
                    }
                }
            }
        } else if (resp instanceof GroupsGetResponse) {
            GroupsGetResponse r = (GroupsGetResponse) resp;
            if (r.hasGroups()) {
                for (int i = 0; i < r.groups.length; i++) {
                    Page u = r.groups[i];
                    if (u != null) {
                        put(u);
                    }
                }
            }
        } else if (resp instanceof FriendsGetResponse) {
            FriendsGetResponse r = (FriendsGetResponse) resp;
            if (r.hasUsers()) {
                for (int i = 0; i < r.users.length; i++) {
                    Page u = r.users[i];
                    if (u != null) {
                        put(u);
                    }
                }
            }
        } else if (resp instanceof UsersGetFollowersResponse) {
            UsersGetFollowersResponse r = (UsersGetFollowersResponse) resp;
            if (r.hasUsers()) {
                for (int i = 0; i < r.users.length; i++) {
                    Page u = r.users[i];
                    if (u != null) {
                        put(u);
                    }
                }
            }
        } else if (resp instanceof GroupsGetMembersResponse) {
            GroupsGetMembersResponse r = (GroupsGetMembersResponse) resp;
            if (r.hasMembers()) {
                for (int i = 0; i < r.members.length; i++) {
                    Page u = r.members[i];
                    if (u != null) {
                        put(u);
                    }
                }
            }
        }
    }

    public static long put(Page p) {
        table.put(String.valueOf(p.getId()), p);
        return p.getId();
    }
}
