package ru.curoviyxru.j2vk;

/**
 *
 * @author curoviyxru
 */
public interface ProgressProvider {

    void setProgress(long i);

    void failed(String s);

    void successful();

    String getName();
}
