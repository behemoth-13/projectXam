package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.Favorite;
import by.afanasyeu.avtoxam.security.SecurityService;
import by.afanasyeu.avtoxam.service.FavoriteService;
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
@RequestMapping("/rest/favorite")
public class FavoriteRestController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private SecurityService securityService;

    @Secured("ROLE_USER")
    @PostMapping(value = "")
    public ResponseEntity insertFavorite(@RequestBody Favorite favorite) {
        Long userId = securityService.getLoggedInUserId();
        favorite.setUserId(userId);
        try {
            favoriteService.insertFavorite(favorite);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}