package org.example.service;

import org.example.dto.CreateUserRequestDto;
import org.example.dto.UpdateUserRequestDto;
import org.example.dto.UserDto;
import org.example.dto.UserViewDto;
import org.example.model.User;

import java.util.List;

public interface UserService {

    public List<UserViewDto> getUsers();

    public User findUserById(String id);

    public UserDto getUserById(String userId);

    public UserDto createUser(CreateUserRequestDto requestDto);

    public UserViewDto updateUser(UpdateUserRequestDto requestDto);

    public void deleteUserById(String id);

}
