package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.Comment;
import by.afanasyeu.avtoxam.dao.entities.DTO.CommentDTO;
import by.afanasyeu.avtoxam.security.SecurityService;
import by.afanasyeu.avtoxam.service.CommentService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 03.08.2017.
 */

@RestController
@RequestMapping("/rest/comment")
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private SecurityService securityService;

    private static final Integer COUNT_RETURNED = 20;

    @Secured("ROLE_USER")
    @PostMapping(value = "")
    public ResponseEntity insertMessage(@RequestBody Comment comment) {
        Long userId = securityService.getLoggedInUserId();
        comment.setUserId(userId);
        try {
            commentService.insertComment(comment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "")
    public ResponseEntity<List<CommentDTO>> getFirstsDefault(@RequestBody Long messageId) {
        return getFirstsFromService(messageId, COUNT_RETURNED);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/{countFirsts}")
    public ResponseEntity<List<CommentDTO>> getFirsts(@RequestBody Long messageId, @PathVariable Integer countFirsts) {
        return getFirstsFromService(messageId, countFirsts);
    }

    private ResponseEntity<List<CommentDTO>> getFirstsFromService(Long messageId, Integer countFirsts) {
        List<CommentDTO> comments;
        try {
            Long userId = securityService.getLoggedInUserId();
            comments = commentService.getFirsts(messageId, userId, countFirsts);
        } catch (ServiceException e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/interval")
    public ResponseEntity<List<CommentDTO>> getFromIntervalDefault(@RequestBody Long messageId, @RequestBody Long first){
        return getFromIntervalFromService(messageId, first, COUNT_RETURNED);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/interval/{count}")
    public ResponseEntity<List<CommentDTO>> getFromInterval(@RequestBody Long messageId, @RequestBody Long first, @PathVariable Integer count){
        return getFromIntervalFromService(messageId, first, count);
    }

    private ResponseEntity<List<CommentDTO>> getFromIntervalFromService(Long messageId, Long first, Integer count) {
        List<CommentDTO> messages;
        try {
            Long userId = securityService.getLoggedInUserId();
            messages = commentService.getFromInterval(messageId, first, count, userId);
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
    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity delete(@PathVariable Long commentId) {
        try {
            Long userId = securityService.getLoggedInUserId();
            commentService.deleteByCommentIdUserId(commentId, userId);
        } catch (Exception e) {
            //log TODO
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}