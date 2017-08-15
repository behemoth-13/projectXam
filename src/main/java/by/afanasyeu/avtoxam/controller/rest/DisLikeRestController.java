package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.Like;
import by.afanasyeu.avtoxam.security.SecurityService;
import by.afanasyeu.avtoxam.service.DisLikeService;
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
 * Controller для операций с {@link Like},
 * имеется ввиду disLike
 * @author Afanasyeu Alexei
 */

@RestController
@RequestMapping("/rest/dis")
public class DisLikeRestController {

    private final DisLikeService disLikeService;

    private final SecurityService securityService;

    private final Validator likeValidator;

    @Autowired
    public DisLikeRestController(DisLikeService disLikeService, SecurityService securityService, @Qualifier("likeValidator") Validator likeValidator) {
        this.disLikeService = disLikeService;
        this.securityService = securityService;
        this.likeValidator = likeValidator;
    }

    @Secured("ROLE_USER")
    @PostMapping(value = "")
    public ResponseEntity insertDisLike(@RequestBody Like disLike, BindingResult result) {
        likeValidator.validate(disLike, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Long userId = securityService.getLoggedInUserId();
        disLike.setUserId(userId);
        try {
            disLikeService.insertDisLike(disLike);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
