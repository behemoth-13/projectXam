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
 * Created by Afanasyeu Alexei on 22.07.2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userMapper.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + " not found.");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_USER));
        if (user.getLogin().equals("Admin")) {
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
