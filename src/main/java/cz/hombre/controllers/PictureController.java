package cz.hombre.controllers;

import cz.hombre.client.FileManager;
import cz.hombre.client.ImageStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PictureController {
    private FileManager imageDataMgr;

    public static final String UPLOAD_PATH = "/upload/{name}";

    @RequestMapping(value = UPLOAD_PATH, method = RequestMethod.POST)
    public
    @ResponseBody
    ImageStatus uploadImage(@PathVariable("name") String name,
                            @RequestParam("data") MultipartFile imageData,
                            HttpServletResponse response) {

        ImageStatus state = new ImageStatus(ImageStatus.ImageState.READY);

        setFileManager();

        try {
            imageDataMgr.saveImageData(name, imageData.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return state;
    }

    public void setFileManager() {

        try {

            imageDataMgr = FileManager.get();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
