package ru.curoviyxru.j2vk.platform;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import ru.curoviyxru.j2vk.ProgressProvider;
import ru.curoviyxru.j2vk.VKConstants;
import ru.curoviyxru.j2vk.api.requests.VKRequest;

/**
 *
 * @author curoviyxru
 */
public class HTTPMEClient implements IHTTPClient {

    public static boolean useFlush = false;

    public byte[] readStream(InputStream inputStream) throws Exception {
        if (inputStream == null) {
            return null;
        }
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

    public String getURLString(String request) {
        try {
            return new String(getURL(request), Charset.current);
        } catch (Exception e) {
            VKConstants.debug(0, e);
        }

        return null;
    }
    
    public Object openHttpConnection(String url, boolean checkForRedir) throws Exception {
        return openHttpConnection(url, checkForRedir, false);
    }

    public Object openHttpConnection(String url, boolean checkForRedir, boolean ignorePost) throws Exception {
            if (VKConstants.apiUrl().startsWith("http:") && url.startsWith("https:")) {
                url = "http" + url.substring(5);
            }
            VKConstants.debug(0, "[OHC]: " + url);
            HttpConnection rawConnection = (HttpConnection) Connector.open(VKConstants.proxyUrl != null && !url.startsWith("http:") ? VKConstants.proxyUrl : url);
            if (VKConstants.proxyUrl != null && !url.startsWith("http:")) {
                rawConnection.setRequestMethod("POST");
            }
            rawConnection.setRequestProperty("User-Agent", VKConstants.api_useragent);
            if (VKConstants.proxyUrl != null && !url.startsWith("http:") && !ignorePost) {
                String boundary = System.currentTimeMillis() + "" + System.currentTimeMillis();
                byte[] boundaryBytes = ("--" + boundary + "\r\n").getBytes(Charset.current);
                byte[] finishBoundaryBytes = ("--" + boundary + "--").getBytes(Charset.current);
                
                rawConnection.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8; boundary=" + boundary);
                OutputStream out = rawConnection.openOutputStream();
                
                out.write(boundaryBytes);
                sendField(out, "proxy_link", url);
                out.write(finishBoundaryBytes);
                out.close();
            }

            if (checkForRedir) {
                String refLink = rawConnection.getHeaderField("Location");
                if (refLink != null) {
                    rawConnection.close();
                    return openHttpConnection(refLink, checkForRedir, ignorePost);
                }
            }

            return rawConnection;
    }

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

    public String urlEncode(String s) {
		if(s == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			int c = chars[i];
			if (65 <= c && c <= 90) {
				sb.append((char) c);
			} else if (97 <= c && c <= 122) {
				sb.append((char) c);
			} else if (48 <= c && c <= 57) {
				sb.append((char) c);
			} else if (c == ' ') {
				sb.append("%20");
			} else if (c == 45 || c == 95 || c == 46 || c == 33 || c == 126 || c == 42 || c == 39 || c == 40
					|| c == 41) {
				sb.append((char) c);
			} else if (c <= 127) {
				sb.append(hex(c));
			} else if (c <= 2047) {
				sb.append(hex(0xC0 | c >> 6));
				sb.append(hex(0x80 | c & 0x3F));
			} else {
				sb.append(hex(0xE0 | c >> 12));
				sb.append(hex(0x80 | c >> 6 & 0x3F));
				sb.append(hex(0x80 | c & 0x3F));
			}
		}
		return sb.toString();
	}

	private static String hex(int i) {
		String s = Integer.toHexString(i);
		return "%" + (s.length() < 2 ? "0" : "") + s;
	}

    public byte[] getURL(String request) {
        try {
            HttpConnection conn = (HttpConnection) openHttpConnection(request, true);
            InputStream i = conn.openInputStream();
            byte[] buf = readStream(i);
            conn.close();
            return buf;
        } catch (Exception ex) {
            //#ifdef DEBUG_MODE
//#                 ex.printStackTrace();
            //#endif
        }

        return null;
    }

    public String uploadFile(String desturl, String field, String fileName, long fileLength, InputStream stream, ProgressProvider pp) throws Exception {
        try {
            String s = _uploadFile(desturl, field, fileName, fileLength, stream, pp);
            if (pp != null) {
                if (s != null) {
                    pp.successful();
                } else {
                    pp.failed("Upload failed.");
                }
            }
            return s;
        } catch (Exception e) {
            if (pp != null) {
                pp.failed(e.toString());
            }
            throw e;
        }
    }
    
    public void sendFile(OutputStream out, String name, InputStream in, String fileName, long fileLength, ProgressProvider pp) throws Exception  {
        fileLength = Math.max(fileLength, in.available());
        
        String o = "Content-Disposition: form-data; name=\"" + urlEncode(name)
                 + "\"; filename=\"" + urlEncode(fileName) + "\"\r\n\r\n";
        out.write(o.getBytes(Charset.current));
        byte[] buffer = new byte[2048];
        long readed = 0;
        for (int n = 0; n >= 0; n = in.read(buffer)) {
            readed += n;
            out.write(buffer, 0, n);
            
            if (useFlush) {
                out.flush();
            }
            if (pp != null) {
                pp.setProgress(Math.max(0, (readed * 100 / fileLength) - 1));
            }
        }
        out.write("\r\n".getBytes(Charset.current));
    }
    
    public void sendField(OutputStream out, String name, String field) throws Exception {
        String o = "Content-Disposition: form-data; name=\"" 
             + urlEncode(name) + "\"\r\n\r\n";
        out.write(o.getBytes(Charset.current));
        out.write(field.getBytes(Charset.current));
        out.write("\r\n".getBytes(Charset.current));
    }

    private String _uploadFile(String desturl, String field, String fileName, long fileLength, InputStream stream, ProgressProvider pp) throws Exception {
        VKConstants.debug(0, "[UPL]: " + desturl);

        String boundary = System.currentTimeMillis() + "" + System.currentTimeMillis();
        byte[] boundaryBytes = ("--" + boundary + "\r\n").getBytes(Charset.current);
        byte[] finishBoundaryBytes = ("--" + boundary + "--").getBytes(Charset.current);
        
        HttpConnection rawConnection = (HttpConnection) openHttpConnection(desturl, false, true);
        if (VKConstants.proxyUrl == null || desturl.startsWith("http:")) rawConnection.setRequestMethod("POST");
        rawConnection.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8; boundary=" + boundary);

        OutputStream out = rawConnection.openOutputStream();
        
        out.write(boundaryBytes);
        
        if (VKConstants.proxyUrl != null && !desturl.startsWith("http:")) sendField(out, "proxy_link", desturl);
        
        out.write(boundaryBytes);
        
        sendFile(out, field, stream, fileName, fileLength, pp);
        
        out.write(finishBoundaryBytes);

        out.close();

        InputStream in = rawConnection.openInputStream();
        String str = new String(readStream(in), Charset.current);

        rawConnection.close();

        if (pp != null) {
            pp.setProgress(100);
        }

        return str;
    }

    public long downloadFile(String url, OutputStream s, ProgressProvider pp, int check) throws Exception {
        try {
            long l = _downloadFile(url, s, pp, check);
            if (pp != null) {
                if (l != 0) {
                    pp.successful();
                } else {
                    pp.failed("File corrupted.");
                }
            }
            return l;
        } catch (Exception e) {
            if (pp != null) {
                pp.failed(e.toString());
            }
            throw e;
        }
    }

    private long _downloadFile(String url, OutputStream oS, ProgressProvider pp, int check) throws Exception {
        HttpConnection rawConnection = (HttpConnection) openHttpConnection(url, true);
        InputStream is = rawConnection.openInputStream();
        long avaliable = rawConnection.getLength();

        /* if (conn.availableSize() < avaliable) {
         is.close();
         rawConnection.close();
         throw new Exception("Not enough space");
         } */

        byte[] buffer = new byte[1024 * 16];
        int length;
        long readed = 0;
        while ((length = is.read(buffer)) != -1) {
            readed += length;
            oS.write(buffer, 0, length);
            oS.flush();

            if (pp != null) {
                pp.setProgress(readed * 100 / avaliable);
            }
        }

        is.close();
        rawConnection.close();

        oS.close();

        if (pp != null) {
            pp.setProgress(100);
        }
        return avaliable;
    }

    public String makeResponseUnsafe(VKRequest request) throws Exception {
        if (request == null) {
            return null;
        }

        String url = request.toString();

        VKConstants.debug(0, "[REQ]: " + url);
        HttpConnection rawConnection = (HttpConnection) openHttpConnection(url, true);
        if (rawConnection == null) throw new Exception("Can't connect to destination.");
        InputStream stream = rawConnection.openInputStream();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        stream.close();
        byte[] bb = result.toByteArray();
        result.close();
        rawConnection.close();
        String raw = new String(bb, Charset.current);

        VKConstants.debug(0, "[RES]: " + raw);
        return raw;
    }
}
