package by.afanasyeu.avtoxam.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Служит для взятия login, id у пользователя, успешно прошедшего аутентификацию
 * и выполняющего запрос
 * @author Afanasyeu Alexei
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public String getLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public Long getLoggedInUserId() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) userDetails).getUserId();
        }
        return null;
    }
}
