package ru.curoviyxru.j2vk.platform;

import java.io.InputStream;
import java.io.OutputStream;
import ru.curoviyxru.j2vk.ProgressProvider;
import ru.curoviyxru.j2vk.api.requests.VKRequest;

/**
 *
 * @author curoviyxru
 */
public interface IHTTPClient {

    public String uploadFile(String desturl, String field, String fileName, long fileLength, InputStream stream, ProgressProvider pp) throws Exception;

    public String makeResponse(VKRequest request);

    public String makeResponseUnsafe(VKRequest request) throws Exception;

    public String urlEncode(String s);

    public Object openHttpConnection(String url, boolean checkForRedir) throws Exception;

    public byte[] readStream(InputStream inputStream) throws Exception;

    public String getURLString(String request);

    public long downloadFile(String url, OutputStream s, ProgressProvider pp, int check) throws Exception;
}
