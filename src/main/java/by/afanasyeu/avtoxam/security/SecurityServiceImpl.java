package by.afanasyeu.avtoxam.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Afanasyeu Alexei on 23.07.2017.
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
