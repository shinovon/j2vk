package ru.curoviyxru.j2vk.api.objects;

/**
 *
 * @author curoviyxru
 */
public interface ImItem {

    public boolean out();

    public int id();

    public long fromId();

    public int getLastTime();

    public boolean hasAttachments();

    public boolean hasReplyMessage();

    public boolean canEdDel();

    public ImItem replyMessage();

    public Attachment[] attachments();

    public String text();

    public long ownerId();

    public boolean isDeleted();

    public String toString(boolean showName, boolean isPMs, boolean shortName, boolean mentionReply, boolean mentionFwd);

    public boolean hasText();

    public boolean hasForwardedMessages();

    public ImItem[] forwardedMessages();
    
}
