//package ru.curoviyxru.vkuserbot;
//Fast-coded HTTPClient implementation for Java SE.

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import ru.curoviyxru.j2vk.ProgressProvider;
import ru.curoviyxru.j2vk.VKConstants;
import ru.curoviyxru.j2vk.api.requests.VKRequest;
import ru.curoviyxru.j2vk.platform.IHTTPClient;

/**
 *
 * @author curoviyxru
 */
public class HTTPSEClient implements IHTTPClient {

    public static String boundary = "---pheonixfileuploaderpheonixtop";
    public static String MIME = "application/octet-stream";

    @Override
    public String uploadFile(String desturl, String field, String fileName, InputStream stream, ProgressProvider pp) throws Exception {
        try {
            String s = _uploadFile(desturl, field, fileName, stream, pp);
            if (pp != null) {
                if (s != null) pp.successful();
                else pp.failed("Upload failed.");
            }
            return s;
        } catch (Exception e) {
            if (pp != null) pp.failed(e.toString());
            throw e;
        }
    }

    private String _uploadFile(String desturl, String field, String fileName, InputStream stream, ProgressProvider pp) throws Exception {
        VKConstants.debug(0, "[UPL]: " + desturl);

        StringBuffer rB = new StringBuffer();
        rB.append("--").append(boundary).append("\n");
        rB.append("Content-Disposition: form-data; name=\"").append(field).append("\"; filename=\"file.").append(getExt(fileName)).append("\"\n");
        rB.append("Content-Type: ").append(MIME).append("\n\n");
        byte[] before = rB.toString().getBytes(Charset.current);
        rB.setLength(0);
        rB.append("\n--").append(boundary).append("--");
        byte[] after = rB.toString().getBytes(Charset.current);
        rB.setLength(0);

        long l = stream.available();

        HttpURLConnection rawConnection = (HttpURLConnection) openHttpConnection(desturl);
        rawConnection.setRequestMethod("POST");
        rawConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        rawConnection.setRequestProperty("Content-Length", String.valueOf(l + before.length + after.length));

        OutputStream out = rawConnection.getOutputStream();

        out.write(before);

        byte[] buffer = new byte[1024 * 16];
        int length;
        long readed = 0;
        while ((length = stream.read(buffer)) != -1) {
            readed += length;
            out.write(buffer, 0, length);

            if (pp != null) pp.setProgress(readed * 100 / l);
            //out.flush();
        }

        stream.close();

        out.write(after);

        out.flush();
        out.close();

        InputStream in;
        try {
            in = rawConnection.getInputStream();
        } catch (Exception e) {
            in = rawConnection.getErrorStream();
        }
        String str = new String(readStream(in), Charset.current);

        rawConnection.disconnect();

        if (pp != null) pp.setProgress(100);

        return str;
    }

    private static String getExt(String path) {
        if (path == null) return null;
        int i = path.lastIndexOf('.');
        if (i == -1) return path;
        return path.substring(i + 1);
    }

    @Override
    public String makeResponse(VKRequest request) {
        if (request == null) {
            return null;
        }

        String str = request.toString();

        VKConstants.debug(0, "[REQ]: " + str);

        String raw = getURLString(str);

        VKConstants.debug(0, "[RES]: " + raw);
        return raw;
    }

    @Override
    public String makeResponseUnsafe(VKRequest request) throws Exception {
        if (request == null) {
            return null;
        }

        String url = request.toString();

        VKConstants.debug(0, "[REQ]: " + url);
        HttpURLConnection rawConnection = (HttpURLConnection) openHttpConnection(url);

        InputStream stream;
        try {
            stream = rawConnection.getInputStream();
        } catch (Exception e) {
            stream = rawConnection.getErrorStream();
        }

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        stream.close();
        byte[] bb = result.toByteArray();
        result.close();
        rawConnection.disconnect();
        String raw = new String(bb, Charset.current);

        VKConstants.debug(0, "[RES]: " + raw);
        return raw;
    }

    @Override
    public String urlEncode(String s) {
        StringBuffer sbuf = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i);
            if ('A' <= ch && ch <= 'Z') // 'A'..'Z'
            {
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') // 'a'..'z'
            {
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') // '0'..'9'
            {
                sbuf.append((char) ch);
            } else if (ch == ' ') // space
            {
                sbuf.append('+');
            } else if (ch == '-' || ch == '_' //these characters don't need encoding
                    || ch == '.' || ch == '*') {
                sbuf.append((char) ch);
            } else if (ch <= 0x007f) // other ASCII
            {
                sbuf.append(hex(ch));
            } else if (ch <= 0x07FF) { // non-ASCII <= 0x7FF
                sbuf.append(hex(0xc0 | (ch >> 6)));
                sbuf.append(hex(0x80 | (ch & 0x3F)));
            } else { // 0x7FF < ch <= 0xFFFF
                sbuf.append(hex(0xe0 | (ch >> 12)));
                sbuf.append(hex(0x80 | ((ch >> 6) & 0x3F)));
                sbuf.append(hex(0x80 | (ch & 0x3F)));
            }
        }
        return sbuf.toString();
    }

    public static String hex(int sym) {
        return (hex.substring(sym * 3, sym * 3 + 3));
    }
    final static String hex = "%00%01%02%03%04%05%06%07%08%09%0a%0b%0c%0d%0e%0f%10%11%12%13%14%15%16%17%18%19%1a%1b%1c%1d%1e%1f"
            + "%20%21%22%23%24%25%26%27%28%29%2a%2b%2c%2d%2e%2f%30%31%32%33%34%35%36%37%38%39%3a%3b%3c%3d%3e%3f"
            + "%40%41%42%43%44%45%46%47%48%49%4a%4b%4c%4d%4e%4f%50%51%52%53%54%55%56%57%58%59%5a%5b%5c%5d%5e%5f"
            + "%60%61%62%63%64%65%66%67%68%69%6a%6b%6c%6d%6e%6f%70%71%72%73%74%75%76%77%78%79%7a%7b%7c%7d%7e%7f"
            + "%80%81%82%83%84%85%86%87%88%89%8a%8b%8c%8d%8e%8f%90%91%92%93%94%95%96%97%98%99%9a%9b%9c%9d%9e%9f"
            + "%a0%a1%a2%a3%a4%a5%a6%a7%a8%a9%aa%ab%ac%ad%ae%af%b0%b1%b2%b3%b4%b5%b6%b7%b8%b9%ba%bb%bc%bd%be%bf"
            + "%c0%c1%c2%c3%c4%c5%c6%c7%c8%c9%ca%cb%cc%cd%ce%cf%d0%d1%d2%d3%d4%d5%d6%d7%d8%d9%da%db%dc%dd%de%df"
            + "%e0%e1%e2%e3%e4%e5%e6%e7%e8%e9%ea%eb%ec%ed%ee%ef%f0%f1%f2%f3%f4%f5%f6%f7%f8%f9%fa%fb%fc%fd%fe%ff";

    @Override
    public Object openHttpConnection(String url) {
        try {
            if (VKConstants.apiUrl().startsWith("http:") && url.startsWith("https:")) url = "http" + url.substring(5);
            VKConstants.debug(0, "[OHC]: " + url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("User-Agent", VKConstants.api_useragent);
            return conn;
        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public byte[] readStream(InputStream inputStream) throws Exception {
        if (inputStream == null) return null;
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        inputStream.close();
        byte[] bb = result.toByteArray();
        result.close();
        return bb;
    }

    @Override
    public String getURLString(String request) {
        try {
            HttpURLConnection rawConnection = (HttpURLConnection) openHttpConnection(request);

            InputStream stream;
            try {
                stream = rawConnection.getInputStream();
            } catch (Exception e) {
                stream = rawConnection.getErrorStream();
            }

            return new String(readStream(stream), Charset.current);
        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public long downloadFile(String url, OutputStream s, ProgressProvider pp, int check) throws Exception {
        try {
            long l = _downloadFile(url, s, pp, check);
            if (pp != null) {
                if (l != 0) pp.successful();
                else pp.failed("File corrupted.");
            }
            return l;
        } catch (Exception e) {
            if (pp != null) pp.failed(e.toString());
            throw e;
        }
    }

    public long _downloadFile(String url, OutputStream oS, ProgressProvider pp, int check) throws Exception {
        HttpURLConnection rawConnection = (HttpURLConnection) openHttpConnection(url);
        InputStream is;
        try {
            is = rawConnection.getInputStream();
        } catch (Exception e) {
            is = rawConnection.getErrorStream();
        }
        long avaliable = rawConnection.getContentLength();

        byte[] buffer = new byte[1024 * 16];
        int length;
        long readed = 0;
        while ((length = is.read(buffer)) != -1) {
            readed += length;
            oS.write(buffer, 0, length);
            oS.flush();

            if (pp != null) pp.setProgress(readed * 100 / avaliable);
        }

        is.close();
        rawConnection.disconnect();

        oS.close();

        if (pp != null) pp.setProgress(100);
        return avaliable;
    }
}
