package cz.hombre.controllers;

import cz.hombre.data.Image;
import cz.hombre.repositories.AuthorRepository;
import cz.hombre.repositories.CommentRepository;
import cz.hombre.repositories.ImageRepository;
import cz.hombre.repositories.TagRepository;
import cz.hombre.services.CommentService;
import cz.hombre.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Controller
public class HomeController {

    private ImageRepository imageRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @RequestMapping("/")
    public String showHome(Model model, @RequestParam("id") String id) {
        if ((null == id) || (id == "")) {
            id = imageRepository.findAll().iterator().next().getId().toString();
        }
        Image image = imageRepository.findOne(UUID.fromString(id));
        String nextImageID = getNextImageID(image.getId());

        model.addAttribute("image", image);
        model.addAttribute("nextImageID", nextImageID);
        return "home";
    }

    private String getNextImageID(UUID id) {
        Iterator<Image> it = imageRepository.findAll().iterator();
        Image first = it.next();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                return it.next().getId().toString();
            }
        }
        return first.getId().toString();
    }
}