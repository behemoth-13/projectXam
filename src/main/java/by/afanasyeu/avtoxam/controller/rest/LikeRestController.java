package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.Like;
import by.afanasyeu.avtoxam.security.SecurityService;
import by.afanasyeu.avtoxam.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller для операций с {@link Like}
 * @author Afanasyeu Alexei
 */

@RestController
@RequestMapping("/rest/like")
public class LikeRestController {

    private final LikeService likeService;

    private final SecurityService securityService;

    private final Validator likeValidator;

    @Autowired
    public LikeRestController(LikeService likeService, SecurityService securityService, @Qualifier("likeValidator") Validator likeValidator) {
        this.likeService = likeService;
        this.securityService = securityService;
        this.likeValidator = likeValidator;
    }

    @Secured("ROLE_USER")
    @PostMapping(value = "")
    public ResponseEntity insertLike(@RequestBody Like like, BindingResult result) {
        likeValidator.validate(like, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Long userId = securityService.getLoggedInUserId();
        like.setUserId(userId);
        try {
            likeService.insertLike(like);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}