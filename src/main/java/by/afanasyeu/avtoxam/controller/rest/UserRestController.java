package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.service.UserService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Afanasyeu Alexei on 20.07.2017.
 */

//curl -H "Content-Type: application/json" -X POST -d '{"id":null,"region":1,"login":"через debug","password":"passDebug","regDate":null}' http://localhost:8080/rest/user
//curl -H "Content-Type: application/json" -X POST -d '{"username":"dfgfccc","password":"passDebug"}' http://localhost:8080/login/


@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "")
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_USER')") //блокирует
    @GetMapping(value = "/{login}")
    public ResponseEntity<UserDTO> getByLogin(@PathVariable String login) {
        UserDTO userDTO;
        try {
            userDTO = userService.getUserDTO(login);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

//    @PostMapping(value = "/singup")
//    public ResponseEntity
}