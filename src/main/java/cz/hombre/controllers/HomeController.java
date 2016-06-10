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
    public String showHome(Model model, @RequestParam(value = "id", required = false) String actual) {
        Image image;

        if(actual == null) {
            image = imageService.getFirstImage();
        } else {
            image = imageService.getNextImageById(UUID.fromString(actual));
        }

        if (image == null) {
            image = imageService.getFirstImage();
        }

        model.addAttribute("image", image);
        model.addAttribute("actual", image.getId().toString());

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
}