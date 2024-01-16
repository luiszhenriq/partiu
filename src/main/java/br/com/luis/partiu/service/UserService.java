package br.com.luis.partiu.service;


import br.com.luis.partiu.dto.user.UserRegisterDto;
import br.com.luis.partiu.dto.user.UserResponseDto;
import br.com.luis.partiu.dto.user.UserUpdateDto;
import br.com.luis.partiu.models.User;
import br.com.luis.partiu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService {


    @Autowired
    private UserRepository repository;

    public UserResponseDto register(UserRegisterDto userRegisterDto) {

        User newUser = new User(userRegisterDto);

        User savedUser = repository.save(newUser);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPassword(),
                savedUser.getAvatarUrl(),savedUser.getGender());
    }

    public UserResponseDto updateUser(UUID id, UserUpdateDto updateDto) {
        User user = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Id não encontrado"));

        user.setAvatarUrl(updateDto.avatarUrl());

        User updatedUser = repository.save(user);

        return new UserResponseDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getPassword(),
                updatedUser.getAvatarUrl(),updatedUser.getGender());

    }

}
