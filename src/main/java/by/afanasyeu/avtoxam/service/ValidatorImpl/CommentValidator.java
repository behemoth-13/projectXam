package by.afanasyeu.avtoxam.service.ValidatorImpl;

import by.afanasyeu.avtoxam.dao.entities.Comment;
import by.afanasyeu.avtoxam.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Afanasyeu Alexei
 */

@Component("commentValidator")
public class CommentValidator implements Validator {

    private final MessageService messageService;

    @Autowired
    public CommentValidator(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Comment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Comment comment = (Comment) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "Required");
        if (comment.getComment().length() < 1 || comment.getComment().length() > 2048) {
            errors.rejectValue("comment", "Size.commentForm.comment");
        }

        if (!messageService.isExistMessage(comment.getMessageId())) {
            errors.rejectValue("comment", "NotExist.commentForm.messageId");
        }
    }
}
