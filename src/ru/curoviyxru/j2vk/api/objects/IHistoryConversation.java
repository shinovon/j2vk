package ru.curoviyxru.j2vk.api.objects;

/**
 *
 * @author curoviyxru
 */
public interface IHistoryConversation {
    boolean canWrite();
    boolean hasChatSettings();
    long getId();
    String getTitle();
    long getLocalId();
    boolean isChat();
}
