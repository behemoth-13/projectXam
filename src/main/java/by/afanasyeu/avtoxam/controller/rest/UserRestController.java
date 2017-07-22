package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.service.UserService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Afanasyeu Alexei on 20.07.2017.
 */

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "")
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
}