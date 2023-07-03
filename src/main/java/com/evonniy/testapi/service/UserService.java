package com.evonniy.testapi.service;

import com.evonniy.testapi.exception.exceptions.YouAreNotAnAdminException;
import com.evonniy.testapi.exception.exceptions.YouAreNotAnOrganizatorException;
import com.evonniy.testapi.exception.exceptions.YouAreNotAnOrganizatorOrUserException;
import com.evonniy.testapi.model.entity.User;
import com.evonniy.testapi.model.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AuthenticationService authenticationService;

    public UserService(
            AuthenticationService authenticationService
    ) {
        this.authenticationService = authenticationService;
    }

    public User checkForAdminAndGetIt() {
        User user = authenticationService.getAndCheckRealUser();
        if (user.getRole() == Role.ADMIN) {
            return user;
        }
        throw new YouAreNotAnAdminException();
    }

    public User checkForUserOrOrganizatorAndGetIt() {
        User user = authenticationService.getAndCheckRealUser();
        if (user.getRole() == Role.ORGANIZATOR || user.getRole() == Role.USER) {
            return user;
        }
        throw new YouAreNotAnOrganizatorOrUserException();
    }

    public User checkForOrganizatorAndGetIt() {
        User user = authenticationService.getAndCheckRealUser();
        if (user.getRole() != Role.ORGANIZATOR) {
            throw new YouAreNotAnOrganizatorException();
        }

        return user;
    }

}
