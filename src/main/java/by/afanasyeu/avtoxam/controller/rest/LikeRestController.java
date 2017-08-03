package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.Like;
import by.afanasyeu.avtoxam.security.SecurityService;
import by.afanasyeu.avtoxam.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Afanasyeu Alexei on 03.08.2017.
 */

@RestController
@RequestMapping("/rest/like")
public class LikeRestController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private SecurityService securityService;

    @Secured("ROLE_USER")
    @PostMapping(value = "")
    public ResponseEntity insertLike(@RequestBody Like like) {
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