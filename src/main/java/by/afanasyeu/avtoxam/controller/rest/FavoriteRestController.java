package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.Favorite;
import by.afanasyeu.avtoxam.security.SecurityService;
import by.afanasyeu.avtoxam.service.FavoriteService;
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
 * Controller для операций с {@link Favorite}
 * @author Afanasyeu Alexei
 */

@RestController
@RequestMapping("/rest/favorite")
public class FavoriteRestController {

    private final FavoriteService favoriteService;

    private final SecurityService securityService;

    private final Validator favoriteValidator;

    @Autowired
    public FavoriteRestController(FavoriteService favoriteService, SecurityService securityService, @Qualifier("favoriteValidator") Validator favoriteValidator) {
        this.favoriteService = favoriteService;
        this.securityService = securityService;
        this.favoriteValidator = favoriteValidator;
    }

    @Secured("ROLE_USER")
    @PostMapping(value = "")
    public ResponseEntity insertFavorite(@RequestBody Favorite favorite, BindingResult result) {
        favoriteValidator.validate(favorite, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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