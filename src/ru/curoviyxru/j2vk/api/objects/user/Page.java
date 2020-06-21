package ru.curoviyxru.j2vk.api.objects.user;

import ru.curoviyxru.j2vk.api.objects.VKSerializableObject;
import ru.curoviyxru.j2vk.api.requests.users.UsersGet;

/**
 *
 * @author curoviyxru
 */
public abstract class Page extends VKSerializableObject {

    public boolean isGroup;

    public Page(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public abstract String getNickname();

    public boolean hasShortName() {
        return !isEmpty(getShortName());
    }

    public Group asGroup() {
        return isGroup ? (Group) this : null;
    }

    public User asUser() {
        return !isGroup ? (User) this : null;
    }

    public abstract String getPhoto_50();

    public abstract String getPhoto_100();

    public abstract String getName(String str);

    public final boolean hasName(String str) {
        return !isEmpty(getName(str));
    }

    public final String getName() {
        return getName(UsersGet.NOM);
    }

    public abstract long getId();

    public final long getOriginalId() {
        return isGroup ? -getId() : getId();
    }

    public final boolean hasPhoto_50() {
        return !isEmpty(getPhoto_50()); //TODO check for URL
    }

    public final boolean hasPhoto_100() {
        return !isEmpty(getPhoto_100()); //TODO check for URL
    }

    public final boolean hasName() {
        return !isEmpty(getName());
    }

    public final boolean hasId() {
        return getId() != 0 && ((!isGroup && getId() > 0) || (isGroup && getId() < 0));
    }

    public final boolean hasStatus() {
        return !isEmpty(getStatus());
    }

    public abstract String getStatus();

    public String getShortName() {
        return getShortName(UsersGet.NOM);
    }

    public abstract String getShortName(String str);

    public String getMessageTitle() {
        return getMessageTitle(UsersGet.NOM);
    }

    public String getFirstName() {
        return getFirstName(UsersGet.NOM);
    }

    public abstract String getFirstName(String s);

    public String getMessageTitle(String str) {
        return isGroup ? getName(str) : getShortName(str);
    }

    public String getStatusChar() {
        if (isGroup) {
            return "";
        }
        User page = asUser();
        if (page.online == 1) {
            if (page.online_app != 0) {
                switch (page.online_app) {
                    case 2274003:
                        return "M";
                    case 3697615:
                        return "M";
                    case 3140623:
                        return "M";
                    case 2685278:
                        return "K";
                    case 6146827:
                        return "M";
                    default:
                        return "O";
                }
            } else if (page.online_mobile == 1) {
                return "M";
            }
            return "O";
        } else {
            return "";
        }
    }

    public static class DeactivationReason {

        public static final String DELETED = "deleted";
        public static final String BANNED = "banned";
    }
}
