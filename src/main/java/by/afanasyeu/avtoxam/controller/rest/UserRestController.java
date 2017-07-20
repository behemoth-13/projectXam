package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.service.UserService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Afanasyeu Alexei on 20.07.2017.
 */

@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{login}")
    public ResponseEntity<UserDTO> getById(@PathVariable String login) {
        System.out.println("ok");
        UserDTO userDTO;
        try {
            userDTO = userService.getUserDTO(login);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
