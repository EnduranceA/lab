package ru.itis.security.jwt.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.itis.repositories.jpa.UserRepositoryJpa;

@Service("customUserServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        return new UserDetailsImpl(userRepositoryJpa.findByEmail(email));
    }
}

