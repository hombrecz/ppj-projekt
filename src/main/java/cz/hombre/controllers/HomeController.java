package cz.hombre.controllers;

import cz.hombre.data.Image;
import cz.hombre.repositories.CommentRepository;
import cz.hombre.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.UUID;

/**
 * @author ondrej.dlabola
 */
@Controller
public class HomeController {

    private ImageRepository imageRepository;
    private CommentRepository commentRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository, CommentRepository commentRepository) {
        this.imageRepository = imageRepository;
        this.commentRepository = commentRepository;
    }

    @RequestMapping("/projekt")
    public String showHome(Model model, @RequestParam(value = "id", required = false) String id) {
        if ((null == id) || (id == "")) {
            id = imageRepository.findAll().iterator().next().getId().toString();
        }
        Image image = imageRepository.findOne(UUID.fromString(id));
        String nextImageID = getNextImageID(image.getId());

        model.addAttribute("image", image);
        model.addAttribute("nextImageID", nextImageID);
        return "projekt";
    }

    @RequestMapping("/likeImg")
    public String likeImage(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id) {
        imageRepository.updateLikesCount(UUID.fromString(id));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/dislikeImg")
    public String dislikeImage(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id) {
        imageRepository.updateDislikesCount(UUID.fromString(id));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/likeComment")
    public String likeComment(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id,
                              @RequestParam(value = "commentId") String commentId) {
        commentRepository.updateLikesCount(UUID.fromString(commentId));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/dislikeComment")
    public String dislikeComment(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id,
                                 @RequestParam(value = "commentId") String commentId) {
        commentRepository.updateDislikesCount(UUID.fromString(commentId));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
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