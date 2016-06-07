package cz.hombre.controllers;

import cz.hombre.data.Image;
import cz.hombre.services.CommentService;
import cz.hombre.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private ImageService imageService;
    private CommentService commentService;

    @Autowired
    public void setImageRepository(ImageService imageService, CommentService commentService) {
        this.imageService = imageService;
        this.commentService = commentService;
    }

    @Value("${pics.path}")
    private String path;

    @RequestMapping("/projekt")
    public String showHome(Model model, @RequestParam(value = "id", required = false) String id) {
        if ((null == id) || (id == "")) {
            id = imageService.getAllImages().iterator().next().getId().toString();
        }
        Image image = imageService.getImageById(UUID.fromString(id));
        String nextImageID = getNextImageID(image.getId());

        model.addAttribute("image", image);
        model.addAttribute("nextImageID", nextImageID);

        if (!image.getUrl().startsWith("http")) {
            model.addAttribute("path", path);
        } else {
            model.addAttribute("path", "");
        }

        return "projekt";
    }

    @RequestMapping("/likeImg")
    public String likeImage(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id) {
        imageService.incrementLikesCount(UUID.fromString(id));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/dislikeImg")
    public String dislikeImage(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id) {
        imageService.incrementDislikesCount(UUID.fromString(id));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/likeComment")
    public String likeComment(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id,
                              @RequestParam(value = "commentId") String commentId) {
        commentService.incrementLikesCount(UUID.fromString(commentId));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    @RequestMapping("/dislikeComment")
    public String dislikeComment(RedirectAttributes redirectAttributes, @RequestParam(value = "id") String id,
                                 @RequestParam(value = "commentId") String commentId) {
        commentService.incrementDislikesCount(UUID.fromString(commentId));
        redirectAttributes.addAttribute("id", id);
        return "redirect:/projekt";
    }

    private String getNextImageID(UUID id) {
        Iterator<Image> it = imageService.getAllImages().iterator();
        Image first = it.next();
        Image next = it.next();

        if (first.getId().equals(id)) {
            return next.getId().toString();
        }

        while (it.hasNext() && (!next.getId().equals(id))) {
            next = it.next();
        }
        if (it.hasNext()) {
            return it.next().getId().toString();
        } else {
            return first.getId().toString();
        }
    }
}