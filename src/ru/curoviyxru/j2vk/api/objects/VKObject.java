package ru.curoviyxru.j2vk.api.objects;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import ru.curoviyxru.j2vk.TextUtil;

import ru.curoviyxru.j2vk.VKConstants;
import ru.curoviyxru.j2vk.api.requests.VKRequest;

public class VKObject extends VKConstants {

    public static String[] MONTH_NAMES = {
        "",
        "jan",
        "feb",
        "mar",
        "apr",
        "may",
        "jun",
        "jul",
        "aug",
        "sep",
        "oct",
        "nov",
        "dec"
    };
    
    public static boolean isEmpty(String str) {
        return TextUtil.isNullOrEmpty(str);
    }

    public static int fromBoolean(boolean bool) {
        return bool ? 1 : 0;
    }

    public static String onlyTimeToString(int date) {
        return TextUtil.getTimeString(date, false);
    }

    public static String dateToString(int date) {
        int[] lD = TextUtil.createDateArray(date);
        return TextUtil.makeTwo(lD[TextUtil.TIME_DAY]) + " " + MONTH_NAMES[lD[TextUtil.TIME_MON]];
    }
    
    public static String timeToString(int date) {
        if ((System.currentTimeMillis() / 1000 / 86400) - (date / 86400) > 0) {
            return dateToString(date);
        } else return onlyTimeToString(date);
    }
    
    public static String trackTimeToString(long date) {
        long hours = date / 60;
        long mins = date % 60;
        return hours + ":" + TextUtil.makeTwo(mins);
    }

    public static String formatArgs(VKRequest args) {
        StringBuffer str = new StringBuffer();

        if (args == null) {
            return str.toString();
        }
        Hashtable table = args.getArguments();
        for (Enumeration args1 = table.keys(); args1.hasMoreElements();) {
            String arg = (String) args1.nextElement();
            String val = (String) table.get(arg);
            if (str.length() > 0) {
                str.append('&');
            }
            str.append(arg).append('=').append(val);
        }

        return str.toString();
    }

    public static String join(String joiner, Vector v) {
        StringBuffer str = new StringBuffer();

        if (v == null || joiner == null) {
            return str.toString();
        }
        for (int i = 0; i < v.size(); i++) {
            Object obj = v.elementAt(i);
            if (obj != null) {
                if (str.length() > 0) {
                    str.append(joiner);
                }
                str.append(obj.toString());
            }
        }

        return str.toString();
    }

    public static boolean fromInteger(int val) {
        return val == 1;
    }
}
