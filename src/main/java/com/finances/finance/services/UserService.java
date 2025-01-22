package com.finances.finance.services;

import com.finances.finance.domain.entities.user.User;
import com.finances.finance.domain.entities.user.UserDto;
import com.finances.finance.domain.entities.user.UserRegisterDto;
import com.finances.finance.domain.entities.user.UserRole;
import com.finances.finance.errors.UserNotFindError;
import com.finances.finance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<UserDetails> userDetails = userRepository.findByEmail(username);

        if (userDetails.isEmpty()) {
            return null;
        } else {
            return userDetails.get();
        }
    }

    public UserDto register(UserRegisterDto userRegisterDto) {

        String password = passwordEncoder.encode(userRegisterDto.password());

        User user = new User();
        user.setName(userRegisterDto.name());
        user.setEmail(userRegisterDto.email());
        user.setBirthday(userRegisterDto.birthday());
        user.setCreatedAt(new Date());
        user.setRole(UserRole.USER);
        user.setPassword(password);

        UUID id = userRepository.save(user).getId();

        return new UserDto(id, user.getName(), user.getEmail(), user.getBirthday());
    }

    public UserDto getUserById(UUID id) {

        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new UserNotFindError("Cannot find user with id: " + id);
        }

        User user = userOpt.get();

        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getBirthday());
        return userDto;

    }

    public List<UserDto> getAll() {

        List<User> users = userRepository.findAll();

        List<UserDto> usersDto = users.stream().map(x -> new UserDto(x.getId(), x.getName(), x.getEmail(), x.getBirthday())).toList();

        return usersDto;
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
