package ru.curoviyxru.j2vk.api.requests.users;

import java.util.Vector;

import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.api.responses.users.UsersGetResponse;

/**
 *
 * @author curoviyxru
 */
public class UsersGet extends VKRequest {

    Vector ids = new Vector();

    public UsersGet() {
        super(UsersGetResponse.class, "users.get");
        setArgument("extended", 1);
        setArgument("fields", fields);
    }
    public String name_case;

    public UsersGet setNameCase(String cases) {
        setArgument("name_case", cases);

        return this;
    }
    public static final String NOM = "nom", DAT = "dat", GEN = "gen", ACC = "acc", INS = "ins", ABL = "abl";

    public UsersGet(long id) {
        this();
        addUserId(id);
    }

    public UsersGet addUserId(long id) {
        if (id < 0) {
            id = id * -1;
        }
        if (ids.indexOf(new Long(id)) != -1) {
            return this;
        }

        ids.addElement(new Long(id));
        updateIds();

        return this;
    }

    public UsersGet removeUserId(long id) {
        if (id < 0) {
            id = id * -1;
        }
        if (ids.indexOf(new Long(id)) == -1) {
            return this;
        }

        ids.removeElement(new Long(id));
        updateIds();

        return this;
    }

    private void updateIds() {
        if (hasUserIds()) {
            setArgument("user_ids", join(",", ids));
        } else {
            setArgument("user_ids", null);
        }
    }

    public boolean hasUserIds() {
        return ids != null && ids.size() > 0;
    }

    public Vector getUserIds() {
        return ids;
    }
}
