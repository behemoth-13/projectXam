package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.security.SecurityService;
import by.afanasyeu.avtoxam.service.UserService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Afanasyeu Alexei on 20.07.2017.
 */

//curl -H "Content-Type:application/json" -X POST -d '{"id":null,"region":1,"login":"через debug","password":"passDebug","regDate":null}' http://localhost:8080/rest/user
//curl -H "Content-Type:application/json" -X POST -d '{"j_username":"testLogin","j_password":"testPassword"}' http://localhost:8080/j_spring_security_check
    //curl -H "Content-Type: application/json" -X GET http://localhost:8080/rest/

// curl -i -X POST -d j_username=temporary -d j_password=temporary -c e:/cookies.txt http://localhost:8080/api/j_spring_security_check
// curl -i -X POST -d username=temporary -d password=temporary -c e:/cookies.txt http://localhost:8080/login
// curl -i -X POST -d j_username=user -d j_password=userPass -c e:/cookies.txt http://localhost:8080/api/j_spring_security_check

// curl -i -H "Content-Type: application/json" -X GET -b e:/cookies.txt http://localhost:8080/api/customer
// curl -i -H "Content-Type: application/json" -X GET -b e:/cookies.txt http://localhost:8080/api/customer/2

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    @Qualifier("userValidator")
    private Validator userValidator;

    @PreAuthorize("permitAll")
    @PostMapping(value = "")
    public ResponseEntity create(@RequestBody User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{login}")
    public ResponseEntity<UserDTO> getByLogin(@PathVariable String login) {
        UserDTO userDTO;
        try {
            userDTO = userService.getUserDTO(login);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PutMapping(value = "/login/{newLogin}")
    public ResponseEntity<UserDTO> updateLogin(@PathVariable String newLogin) {
        String oldLogin = securityService.getLoggedInUsername();
        try {
            userService.updateLogin(oldLogin, newLogin);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PutMapping(value = "/region/{newRegion}")
    public ResponseEntity<UserDTO> updateRegion(@PathVariable Integer newRegion) {
        Long userId = securityService.getLoggedInUserId();
        try {
            userService.updateRegion(userId, newRegion);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PutMapping(value = "/password/{newPassword}")
    public ResponseEntity<UserDTO> updateRegion(@PathVariable String newPassword) {
        Long userId = securityService.getLoggedInUserId();
        try {
            userService.updatePassword(userId, newPassword);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @DeleteMapping(value = "")
    public ResponseEntity delete() {
        try {
            Long userId = securityService.getLoggedInUserId();
            userService.deleteById(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}