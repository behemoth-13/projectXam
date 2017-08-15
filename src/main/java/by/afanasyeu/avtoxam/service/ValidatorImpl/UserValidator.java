package by.afanasyeu.avtoxam.service.ValidatorImpl;

import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.service.RegionService;
import by.afanasyeu.avtoxam.service.UserService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Afanasyeu Alexei
 */
@Component("userValidator")
public class UserValidator implements Validator {

    private final UserService userService;
    private final RegionService regionService;

    @Autowired
    public UserValidator(UserService userService, RegionService regionService) {
        this.userService = userService;
        this.regionService = regionService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getLogin().length() < 2 || user.getLogin().length() > 15) {
            errors.rejectValue("login", "Size.userForm.login");
        }

        if (!regionService.isExistRegion(user.getRegion())) {
            errors.rejectValue("region", "NotExist.userForm.region");
        }

        try {
            userService.getUserDTO(user.getLogin());
        } catch (ServiceException e) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 10) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
