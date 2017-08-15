package by.afanasyeu.avtoxam.security;


import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.dao.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Предоставляется права пользователям,
 *  участвует в аутентификации.
 *
 *
 * @author Afanasyeu Alexei
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String ROLE_USER = "ROLE_USER";

    /**
     * Присваивается пользователю с именем
     * {@value ADMIN_NAME}
     */
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private static final String ADMIN_NAME = "Admin";

    private final UserMapper userMapper;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userMapper.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + " not found.");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_USER));
        if (user.getLogin().equals(ADMIN_NAME)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
        }
        return new UserDetailsImpl(user.getId(), user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
