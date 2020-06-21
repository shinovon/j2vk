package ru.curoviyxru.j2vk;

import java.io.InputStream;
import org.json.me.JSONObject;
import ru.curoviyxru.j2vk.api.objects.Attachment;
import ru.curoviyxru.j2vk.api.objects.attachments.Photo;
import ru.curoviyxru.j2vk.api.requests.docs.DocsGetMessagesUploadServer;
import ru.curoviyxru.j2vk.api.requests.docs.DocsSave;
import ru.curoviyxru.j2vk.api.requests.photos.PhotosGetMessagesUploadServer;
import ru.curoviyxru.j2vk.api.requests.photos.PhotosSaveMessagesPhoto;
import ru.curoviyxru.j2vk.api.responses.docs.DocsGetMessagesUploadServerResponse;
import ru.curoviyxru.j2vk.api.responses.docs.DocsSaveResponse;
import ru.curoviyxru.j2vk.api.responses.photos.PhotosGetMessagesUploadServerResponse;
import ru.curoviyxru.j2vk.api.responses.photos.PhotosSaveMessagesPhotoResponse;

/**
 *
 * @author curoviyxru
 */
public class Uploader {

    public static Photo uploadMessagePhoto(long pid, String fileName, long fileLength, InputStream stream, ProgressProvider pp) throws Exception {
        PhotosGetMessagesUploadServerResponse resp = (PhotosGetMessagesUploadServerResponse) new PhotosGetMessagesUploadServer(pid).execute();

        if (resp != null && resp.hasURL()) {
            String respStr = HTTPClient.uploadFile(resp.url, "photo", fileName, fileLength, stream, pp);
            JSONObject obj = new JSONObject(respStr);

            PhotosSaveMessagesPhotoResponse respphoto = (PhotosSaveMessagesPhotoResponse) new PhotosSaveMessagesPhoto(obj.optString("photo"), obj.optLong("server"), obj.optString("hash")).execute();
            if (respphoto != null) {
                return respphoto.photo;
            }
        }

        return null;
    }

    public static Attachment uploadMessageDocument(long pid, String fileName, long fileLength, InputStream stream, ProgressProvider pp) throws Exception {
        DocsGetMessagesUploadServerResponse resp = (DocsGetMessagesUploadServerResponse) new DocsGetMessagesUploadServer(pid).execute();

        if (resp != null && resp.hasURL()) {
            String respStr = HTTPClient.uploadFile(resp.upload_url, "file", fileName, fileLength, stream, pp);
            JSONObject obj = new JSONObject(respStr);

            DocsSaveResponse respdoc = (DocsSaveResponse) new DocsSave(obj.optString("file")).execute();
            if (respdoc != null) {
                return respdoc.attachment;
            }
        }

        return null;
    }
}
