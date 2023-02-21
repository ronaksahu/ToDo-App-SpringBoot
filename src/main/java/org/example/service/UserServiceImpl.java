package org.example.service;

import org.example.dto.CreateUserRequestDto;
import org.example.dto.UpdateUserRequestDto;
import org.example.dto.UserDto;
import org.example.dto.UserViewDto;
import org.example.dto.converter.UserDtoConverter;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserServiceImpl(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserViewDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userDtoConverter.convertToViewDto(user))
                .collect(Collectors.toList());
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User could not find by id: " + id));
    }

    public UserDto getUserById(String userId) {
        return userDtoConverter.convert(findUserById(userId));
    }

    public UserDto createUser(CreateUserRequestDto requestDto) {
        final User user = new User( "",
                requestDto.getUsername(),
                requestDto.getPassword(),
                new HashSet<>());
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserViewDto updateUser(UpdateUserRequestDto requestDto) {
        final User user = findUserById(requestDto.getId());
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        return userDtoConverter.convertToViewDto(userRepository.save(user));
    }

    public void deleteUserById(String id) {
        final User user = findUserById(id);
        userRepository.deleteById(user.getId());
    }


}
