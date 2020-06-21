package ru.curoviyxru.j2vk;

import java.io.InputStream;
import java.io.OutputStream;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.platform.IHTTPClient;
import ru.curoviyxru.j2vk.platform.ITokener;

/**
 *
 * @author curoviyxru
 */
public class HTTPClient {

    public static IHTTPClient client;
    public static ITokener tokener;

    public static String uploadFile(String desturl, String field, String fileName, long fileLength, InputStream stream, ProgressProvider pp) throws Exception {
        return client.uploadFile(desturl, field, fileName, fileLength, stream, pp);
    }

    public static String makeResponse(VKRequest request) {
        return client.makeResponse(request);
    }

    public static String makeResponseUnsafe(VKRequest request) throws Exception {
        return client.makeResponseUnsafe(request);
    }

    public static String urlEncode(String s) {
        return client.urlEncode(s);
    }

    public static Object openHttpConnection(String url, boolean checkForRedir) throws Exception {
        return client.openHttpConnection(url, checkForRedir);
    }

    public static byte[] readStream(InputStream inputStream) throws Exception {
        return client.readStream(inputStream);
    }

    public static String getURLString(String request) {
        return client.getURLString(request);
    }

    public static long downloadFile(String url, OutputStream s, ProgressProvider pp, int check) throws Exception {
        return client.downloadFile(url, s, pp, check);
    }

    public static String requestToken() {
        if (tokener == null) return null;
        return tokener.requestToken();
    }
}
