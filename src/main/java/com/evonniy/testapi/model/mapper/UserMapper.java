package com.evonniy.testapi.model.mapper;

import com.evonniy.testapi.model.dto.UserDto;
import com.evonniy.testapi.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getUsername());
    }
}
