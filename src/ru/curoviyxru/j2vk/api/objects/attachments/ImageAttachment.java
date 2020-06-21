package ru.curoviyxru.j2vk.api.objects.attachments;

/**
 *
 * @author curoviyxru
 */
public interface ImageAttachment {
    String getURL(int nearWidth);
    int getWidth(int nearWidth);
    int getHeight(int nearHeight);
}
