package com.evonniy.testapi.config.security.jaas;


import com.evonniy.testapi.exception.exceptions.UserNotFoundException;
import com.evonniy.testapi.model.entity.User;
import com.evonniy.testapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.jaas.AuthorityGranter;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class AuthorityGranterImpl implements AuthorityGranter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityGranterImpl.class);
    private final UserRepository userRepository;

    @Override
    public Set<String> grant(Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return Collections.singleton(user.get().getRole().name());
        }

        throw new UserNotFoundException();
    }
}
