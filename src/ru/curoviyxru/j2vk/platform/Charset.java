package ru.curoviyxru.j2vk.platform;

/**
 *
 * @author curoviyxru
 */
public class Charset {
    
    public static String current;
    
    static {
        try {
            String s = new String(new byte[] {}, "UTF8");
            if (s.length() == 0) {
                current = "UTF8";
            }
        } catch (Exception e) {
            current = "UTF-8";
        }
    }
}
