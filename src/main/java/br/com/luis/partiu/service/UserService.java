package br.com.luis.partiu.service;


import br.com.luis.partiu.dto.user.UserRegisterDto;
import br.com.luis.partiu.dto.user.UserResponseDto;
import br.com.luis.partiu.dto.user.UserUpdateDto;
import br.com.luis.partiu.models.Gender;
import br.com.luis.partiu.models.User;
import br.com.luis.partiu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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

    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        Page<User> userPage = repository.findAll(pageable);
        return mapUserPage(userPage, pageable);
    }

    public Page<UserResponseDto> getByGender(Gender gender, Pageable pageable) {
        Page<User> userPage = repository.findByGender(gender, pageable);
        return mapUserPage(userPage, pageable);
    }

    public UserResponseDto getById(UUID id) {
        User user = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Id não encontrado"));

        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.getAvatarUrl(),user.getGender());

    }

    private Page<UserResponseDto> mapUserPage(Page<User> userPage, Pageable pageable) {
        List<UserResponseDto> userList = userPage.getContent().stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getAvatarUrl(),
                        user.getGender()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(userList, pageable, userPage.getTotalElements());
    }


    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
