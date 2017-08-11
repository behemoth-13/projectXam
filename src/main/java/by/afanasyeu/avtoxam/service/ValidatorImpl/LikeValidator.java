package by.afanasyeu.avtoxam.service.ValidatorImpl;

import by.afanasyeu.avtoxam.dao.entities.Like;
import by.afanasyeu.avtoxam.service.CommentService;
import by.afanasyeu.avtoxam.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Afanasyeu Alexei on 11.08.2017.
 */

@Component("likeValidator")
public class LikeValidator implements Validator {

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommentService commentService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Like.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Like like = (Like) o;

        if ((like.getMessageId() != null && like.getCommentId() != null) ||
                (like.getMessageId() == null && like.getCommentId() == null)) {
            errors.rejectValue("like", "Ambiguous.likeForm.messageIdOrCommentId");
        }

        if (like.getMessageId() != null) {
            if (!messageService.isExistMessage(like.getMessageId())) {
                errors.rejectValue("like", "NotExist.likeForm.messageId");
            }
        } else if (like.getCommentId() != null) {
            if (!commentService.isExistComment(like.getCommentId())) {
                errors.rejectValue("like", "NotExist.likeForm.commentId");
            }
        }
    }
}
