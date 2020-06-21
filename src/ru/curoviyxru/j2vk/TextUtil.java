 package ru.curoviyxru.j2vk;

import java.util.TimeZone;
import java.util.Vector;

/**
 *
 * @author TomClaw, curoviyxru
 */
public class TextUtil {

    private final static String error_str = "[incorrect data]";
    final public static int TIME_SECOND = 0;
    final public static int TIME_MINUTE = 1;
    final public static int TIME_HOUR = 2;
    final public static int TIME_DAY = 3;
    final public static int TIME_MON = 4;
    final public static int TIME_YEAR = 5;
    final private static byte[] dayCounts = new byte[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static int gmtOffset = 3 * 3600;
    public static boolean summerTime = false;
    public static int dOffset = TimeZone.getDefault().getRawOffset() / 1000;

    public static long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static long getCurrentTimeGMT() {
        return getCurrentTime() + getGmtOffset();
    }

    public static long getMentionedTimeGMT(long time) {
        return time + getGmtOffset();
    }

    public static int getGmtOffset() {
        return dOffset;
    }

    /* Generates seconds count from 1st Jan 1970 till mentioned date */
    public static long createTimeLong(int year, int mon, int day, int hour,
            int min, int sec) {
        int day_count, i, febCount;

        day_count = (year - 1970) * 365 + day;
        day_count += (year - 1968) / 4;
        if (year >= 2000) {
            day_count--;
        }

        if ((year % 4 == 0) && (year != 2000)) {
            day_count--;
            febCount = 29;
        } else {
            febCount = 28;
        }

        for (i = 0; i < mon - 1; i++) {
            day_count += (i == 1) ? febCount : dayCounts[i];
        }

        return day_count * 24L * 3600L + hour * 3600L + min * 60L + sec;
    }

    // Creates array of calendar values form value of seconds since 1st jan 1970 (GMT)
    public static int[] createDateArray(long value) {
        int total_days, last_days, i;
        int sec, min, hour, day, mon, year;

        sec = (int) (value % 60);

        min = (int) ((value / 60) % 60); // min
        value -= 60 * min;

        hour = (int) ((value / 3600) % 24); // hour
        value -= 3600 * hour;

        total_days = (int) (value / (3600 * 24));

        year = 1970;
        for (;;) {
            last_days = total_days - ((year % 4 == 0) && (year != 2000) ? 366 : 365);
            if (last_days <= 0) {
                break;
            }
            total_days = last_days;
            year++;
        } // year

        int febrDays = ((year % 4 == 0) && (year != 2000)) ? 29 : 28;

        mon = 1;
        for (i = 0; i < 12; i++) {
            last_days = total_days - ((i == 1) ? febrDays : dayCounts[i]);
            if (last_days <= 0) {
                break;
            }
            mon++;
            total_days = last_days;
        } // mon

        day = total_days; // day

        return new int[]{sec, min, hour, day, mon, year};
    }

    /**
     * Creates string for time delay *
     */
    public static String delayToString(long seconds) {
        StringBuffer buf = new StringBuffer();
        int days = (int) (seconds / 86400);
        seconds %= 86400;
        int hours = (int) (seconds / 3600);
        seconds %= 3600;
        //TODO: locale "days", "hours", "minutes" 
        int minutes = (int) (seconds / 60);

        if (days != 0) {
            buf.append(days).append(' ').append(
                    ("days")).append(' ');
        }
        if (hours != 0) {
            buf.append(hours).append(' ').append(
                    ("hours")).append(' ');
        }
        if (minutes != 0) {
            buf.append(minutes).append(' ').append(
                    ("minutes"));
        }

        return buf.toString();
    }

    public static String makeTwo(long number) {
        if (number < 10) {
            return ("0" + String.valueOf(number));
        } else {
            return (String.valueOf(number));
        }
    }

    /* Show date string */
    public static String getDateString(long date, boolean time) {
        if (date == 0) {
            return error_str;
        }

        int[] loclaDate = createDateArray(date);

        StringBuffer sb = new StringBuffer();

        sb.append(makeTwo(loclaDate[TIME_DAY])).append('.').append(
                makeTwo(loclaDate[TIME_MON])).append('.').append(
                loclaDate[TIME_YEAR]);
        if (time) {
            sb.append(' ').append(makeTwo(loclaDate[TIME_HOUR])).append(
                    ':').append(makeTwo(loclaDate[TIME_MINUTE]));
        }

        return sb.toString();
    }

    /* Show time string */
    public static String getTimeStringUTC(long date, boolean seconds) {
        if (date == 0) {
            return error_str;
        }

        int[] loclaDate = createDateArray(date);

        StringBuffer sb = new StringBuffer();

        sb.append(makeTwo(loclaDate[TIME_HOUR])).append(':').append(
                makeTwo(loclaDate[TIME_MINUTE]));

        if (seconds) {
            sb.append(':').append(makeTwo(loclaDate[TIME_SECOND]));
        }

        return sb.toString();
    }

    public static String getUtcTimeString(long date) {
        if (date == 0) {
            return error_str;
        }

        int[] loclaDate = createDateArray(date);

        StringBuffer sb = new StringBuffer();

        sb.append(makeTwo(loclaDate[TIME_YEAR])).append('-').append(
                makeTwo(loclaDate[TIME_MON])).append('-').
                append(makeTwo(loclaDate[TIME_DAY])).
                append('T').append(makeTwo(loclaDate[TIME_HOUR])).
                append(':').append(makeTwo(loclaDate[TIME_MINUTE])).
                append(':').append(makeTwo(loclaDate[TIME_SECOND])).
                append('Z');

        return sb.toString();
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static long getUtcTimeLong(String stamp, boolean isLocalized) {
        /**
         * Checking input *
         */
        if (!isNullOrEmpty(stamp)) {
            /**
             * Time from stamp by XEP-0082 *
             */
            int tIndex = stamp.indexOf('T');
            /**
             * Checking for date and time *
             */
            if (tIndex != -1) {
                String date = stamp.substring(0, tIndex);
                /**
                 * Calculating time *
                 */
                int yIndex = date.indexOf('-');
                int rYears = Integer.parseInt(date.substring(0, yIndex));
                int mIndex = date.indexOf('-', yIndex + 1);
                int rMonths = Integer.parseInt(date.substring(yIndex + 1, mIndex));
                int rDays = Integer.parseInt(date.substring(mIndex + 1));
                String time = stamp.substring(tIndex + 1);
                int zIndex = Math.max(time.indexOf('+'), time.indexOf('-'));
                zIndex = Math.max(zIndex, time.indexOf('Z'));
                /**
                 * Checking for time zone *
                 */
                if (zIndex != -1) {
                    String clearTime = time.substring(0, zIndex);
                    String timeZone = time.substring(zIndex);
                    /**
                     * Calculating time *
                     */
                    int hIndex = clearTime.indexOf(':');
                    int rHours = Integer.parseInt(clearTime.substring(0, hIndex));
                    mIndex = clearTime.indexOf(':', hIndex + 1);
                    int rMinutes = Integer.parseInt(clearTime.substring(hIndex + 1, mIndex));
                    int rSeconds = Integer.parseInt(clearTime.substring(mIndex + 1));
                    long timeLong = TextUtil.createTimeLong(rYears, rMonths, rDays, rHours, rMinutes, rSeconds);
                    /**
                     * Checking for UTC *
                     */
                    if (!timeZone.equals("Z") && !isLocalized) {
                        boolean isPlus = (timeZone.charAt(0) == '+');
                        int dIndex = timeZone.indexOf(':');
                        /**
                         * Checking for delimiter *
                         */
                        if (dIndex != -1) {
                            timeLong -= (((isPlus ? 1 : -1)
                                    * Integer.parseInt(timeZone.substring(1, dIndex))) * 60
                                    + Integer.parseInt(timeZone.substring(dIndex + 1))) * 60;
                        }
                    } else {
                        /**
                         * Converting to local time *
                         */
                        timeLong += TextUtil.getGmtOffset();
                    }
                    return timeLong;
                }
            }
        }
        return 0;
    }

    static long getMillisOffset() {
        return dOffset;
    }

    public static String easyReplace(String s, String target, String replacement) {
        String[] split = easySplit(s, target);
        if (split == null) return null;
        if (split.length == 1) return split[0];
        return split[0] + replacement + split[1];
    }

    public static String[] easySplit(String s, String target) {
        if (s == null) return null;
        if (target == null) return new String[]{s};
        int index = s.indexOf(target);
        String[] split = new String[index != -1 ? 2 : 1];
        if (index == -1) {
            split[0] = s;
            return split;
        }

        split[1] = s.substring(index + target.length());
        split[0] = s.substring(0, s.length() - target.length() - split[1].length());
        return split;
    }

    public static Vector split(String splitStr, String delimiter) {
        StringBuffer token = new StringBuffer();
        Vector tokens = new Vector();

        char[] chars = splitStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            token.append(chars[i]);
            if (token.toString().indexOf(delimiter) != -1) {
                token.setLength(token.length() - delimiter.length());
                tokens.addElement(token.toString());
                token.setLength(0);
            }
        }

        if (token.length() > 0) {
            tokens.addElement(token.toString());
        }
        return tokens;
    }

    public static String[] splitArray(String s, String d) {
        Vector tokens = s == null || d == null ? new Vector(1, 1) : split(s, d);
        if (s != null && d == null) tokens.addElement(s);
        // convert the vector into an array
        String[] splitArray = new String[tokens.size()];
        tokens.copyInto(splitArray);
        return splitArray;
    }

    public static String join(String delimiter, Vector v) {
        StringBuffer buff = new StringBuffer();
        if (v != null) {
            for (int i = 0; i < v.size(); i++) {
                buff.append(v.elementAt(i));
                if (i != v.size() - 1) {
                    buff.append(delimiter);
                }
            }
        }
        return buff.toString();
    }

    public static String getTimeString(long time, boolean seconds) {
        return getTimeStringUTC(time + dOffset, seconds);
    }

    public static int[] parseDateString(String date) {
        if (date == null) {
            return null;
        }
        int[] dateArray = new int[6];
        Vector v = split(date, ".");
        if (v.size() > 2) {
            try {
                dateArray[5] = Integer.parseInt((String) v.elementAt(2));
            } catch (Exception e) {
            }
        }
        if (v.size() > 1) {
            try {
                dateArray[4] = Integer.parseInt((String) v.elementAt(1));
            } catch (Exception e) {
            }
        }
        if (v.size() > 0) {
            try {
                dateArray[3] = Integer.parseInt((String) v.elementAt(0));
            } catch (Exception e) {
            }
        }
        return dateArray;
    }
}
