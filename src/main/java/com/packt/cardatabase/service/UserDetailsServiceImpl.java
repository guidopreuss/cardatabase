package com.packt.cardatabase.service;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User.
        UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.
        UserDetailsService;
import org.springframework.security.core.userdetails.
        UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.packt.cardatabase.domain.AppUser;
import com.packt.cardatabase.domain.AppUserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final AppUserRepository repository;
    public UserDetailsServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        logger.info("GP in UserDeteilaServiceImpl.loadUserByUsername " + username);
        Optional<AppUser> user = repository.findByUsername(username);
        UserBuilder builder = null;
        if (user.isPresent()) {
            logger.info("GP user is present");
            AppUser currentUser = user.get();
            logger.info("GP currentUser: " + currentUser.getUsername() + " " + currentUser.getPassword() + " " + currentUser.getRole());
            builder = org.springframework.security.core.userdetails.
                    User.withUsername(username);
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}

