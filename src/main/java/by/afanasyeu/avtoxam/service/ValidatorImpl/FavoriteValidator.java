package by.afanasyeu.avtoxam.service.ValidatorImpl;

import by.afanasyeu.avtoxam.dao.entities.Favorite;
import by.afanasyeu.avtoxam.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Afanasyeu Alexei on 11.08.2017.
 */

@Component("favoriteValidator")
public class FavoriteValidator implements Validator{

    @Autowired
    private MessageService messageService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Favorite.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Favorite favorite = (Favorite) o;

        if (!messageService.isExistMessage(favorite.getMessageId())) {
            errors.rejectValue("favorite", "NotExist.favoriteForm.messageId");
        }
    }
}
