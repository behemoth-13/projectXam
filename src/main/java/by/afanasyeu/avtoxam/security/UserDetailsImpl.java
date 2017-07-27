package by.afanasyeu.avtoxam.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Afanasyeu Alexei on 27.07.2017.
 */

public class UserDetailsImpl extends User {
    private final Long userId;

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, authorities);
        System.out.println("in UserDetailsImpl id is " + userId);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
