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

    @RequestMapping("/projekt")
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
        Image next = it.next();

        if (first.getId() == id) {
            return next.getId().toString();
        }

        while (it.hasNext() && (next.getId() != id)) {
            next = it.next();
        }
        if (it.hasNext()) {
            return it.next().getId().toString();
        } else {
            return first.getId().toString();
        }
    }
}