package by.afanasyeu.avtoxam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
            System.out.println("instanceof UserDetailsImpl");
            return ((UserDetailsImpl) userDetails).getUsername();
        }
        System.out.println(" not instanceof UserDetailsImpl");
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
