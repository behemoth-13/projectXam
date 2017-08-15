package by.afanasyeu.avtoxam.service.ValidatorImpl;

import by.afanasyeu.avtoxam.dao.entities.Message;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Afanasyeu Alexei
 */

@Component("messageValidator")
public class MessageValidator implements Validator{


    @Override
    public boolean supports(Class<?> aClass) {
        return Message.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Message message = (Message) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "Required");
        if (message.getMessage().length() < 1 || message.getMessage().length() > 2048) {
            errors.rejectValue("message", "Size.messageForm.message");
        }
    }
}
