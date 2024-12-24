package com.finances.finance.domain.mappers;

import com.finances.finance.domain.entities.user.User;
import com.finances.finance.domain.entities.user.UserDto;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserMapper {

    Map<String, Object> dictionary = new HashMap<>();

    public UserDto userToDto(User user) {

        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBirthday()
        );

        return userDto;

    }


}
