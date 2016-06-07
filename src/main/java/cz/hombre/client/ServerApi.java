package cz.hombre.client;

import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

public interface ServerApi {

    public static final String UPLOAD_PATH = "/upload/{name}";

    @Multipart
    @POST(UPLOAD_PATH)
    public ImageStatus uploadImage(@Path("name") String name, @Part("data") TypedFile imageData);
}

