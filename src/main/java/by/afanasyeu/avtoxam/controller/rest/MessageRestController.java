package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.entities.Message;
import by.afanasyeu.avtoxam.security.SecurityService;
import by.afanasyeu.avtoxam.service.MessageService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 28.07.2017.
 */

@RestController
@RequestMapping("/rest/message")
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    @Qualifier("messageValidator")
    private Validator messageValidator;

    private static final Integer COUNT_RETURNED = 20;



    @Secured("ROLE_USER")
    @PostMapping(value = "")
    public ResponseEntity insertMessage(@RequestBody Message message, BindingResult result) {
        messageValidator.validate(message, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Long userId = securityService.getLoggedInUserId();
        message.setUserId(userId);
        try {
            messageService.insertMessage(message);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Secured("ROLE_USER")
    @GetMapping(value = "")
    public ResponseEntity<List<MessageDTO>> getLastDefault() {
        return getLastFromService(COUNT_RETURNED);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{countLasts}")
    public ResponseEntity<List<MessageDTO>> getLasts(@PathVariable Integer countLasts) {
        return getLastFromService(countLasts);
    }

    private ResponseEntity<List<MessageDTO>> getLastFromService(Integer countLast) {
        List<MessageDTO> messages;
        try {
            Long userId = securityService.getLoggedInUserId();
            messages = messageService.getLasts(countLast, userId);
        } catch (ServiceException e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }



    @Secured("ROLE_USER")
    @DeleteMapping(value = "/{messageId}")
    public ResponseEntity delete(@PathVariable Long messageId) {
        try {
            Long userId = securityService.getLoggedInUserId();
            messageService.deleteByMessageIdUserId(messageId, userId);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Secured("ROLE_USER")
    @GetMapping(value = "/interval")
    public ResponseEntity<List<MessageDTO>> getFromIntervalDefault(@RequestBody Long first){
        return getFromIntervalFromService(first, COUNT_RETURNED);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/interval/{count}")
    public ResponseEntity<List<MessageDTO>> getFromInterval(@RequestBody Long first, @PathVariable Integer count){
        return getFromIntervalFromService(first, count);
    }

    private ResponseEntity<List<MessageDTO>> getFromIntervalFromService(Long first, Integer count) {
        List<MessageDTO> messages;
        try {
            Long userId = securityService.getLoggedInUserId();
            messages = messageService.getFromInterval(first, count, userId);
        } catch (ServiceException e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }



    @Secured("ROLE_USER")
    @GetMapping(value = "/since/{messageId}")
    public ResponseEntity<Integer> getCountSince(@PathVariable Long messageId){
        Integer count;
        try {
            count = messageService.getCountSinceById(messageId);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(count, HttpStatus.OK);
    }



    @Secured("ROLE_USER")
    @GetMapping(value = "/liked")
    public ResponseEntity<List<MessageDTO>> getLikedDefault() {
        return getLastFromService(COUNT_RETURNED);

    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/liked/{countLasts}")
    public ResponseEntity<List<MessageDTO>> getLiked(@PathVariable Integer countLasts) {
        return getLastFromService(countLasts);
    }

    private ResponseEntity<List<MessageDTO>> getLikedFromService(Integer countLast) {
        List<MessageDTO> messages;
        try {
            Long userId = securityService.getLoggedInUserId();
            messages = messageService.getLastLikedMessage(countLast, userId);
        } catch (ServiceException e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }



    @Secured("ROLE_USER")
    @GetMapping(value = "/liked/interval")
    public ResponseEntity<List<MessageDTO>> getLikedFromIntervalDefault(@RequestBody Long first){
        return getFromIntervalFromService(first, COUNT_RETURNED);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/liked/interval/{count}")
    public ResponseEntity<List<MessageDTO>> getLikedFromInterval(@RequestBody Long first, @PathVariable Integer count){
        return getFromIntervalFromService(first, count);
    }

    private ResponseEntity<List<MessageDTO>> getLikedFromIntervalFromService(Long first, Integer count) {
        List<MessageDTO> messages;
        try {
            Long userId = securityService.getLoggedInUserId();
            messages = messageService.getLikedMessageFromInterval(first, count, userId);
        } catch (ServiceException e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }



    @Secured("ROLE_USER")
    @GetMapping(value = "/favorite")
    public ResponseEntity<List<MessageDTO>> getFavoriteDefault() {
        return getLastFromService(COUNT_RETURNED);

    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/favorite/{countLasts}")
    public ResponseEntity<List<MessageDTO>> getFavorite(@PathVariable Integer countLasts) {
        return getLastFromService(countLasts);
    }

    private ResponseEntity<List<MessageDTO>> getFavoriteFromService(Integer countLast) {
        List<MessageDTO> messages;
        try {
            Long userId = securityService.getLoggedInUserId();
            messages = messageService.getLastFavoriteMessage(countLast, userId);
        } catch (ServiceException e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/favorite/interval")
    public ResponseEntity<List<MessageDTO>> getFavoriteFromIntervalDefault(@RequestBody Long first){
        return getFromIntervalFromService(first, COUNT_RETURNED);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/favorite/interval/{count}")
    public ResponseEntity<List<MessageDTO>> getFavoriteFromInterval(@RequestBody Long first, @PathVariable Integer count){
        return getFromIntervalFromService(first, count);
    }

    private ResponseEntity<List<MessageDTO>> getFavoriteFromIntervalFromService(Long first, Integer count) {
        List<MessageDTO> messages;
        try {
            Long userId = securityService.getLoggedInUserId();
            messages = messageService.getFavoriteMessageFromInterval(first, count, userId);
        } catch (ServiceException e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
