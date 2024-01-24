package br.com.luis.partiu.service;


import br.com.luis.partiu.dto.user.UserLoginRequestDto;
import br.com.luis.partiu.dto.user.UserRegisterDto;
import br.com.luis.partiu.dto.user.UserResponseDto;
import br.com.luis.partiu.dto.user.UserUpdateDto;
import br.com.luis.partiu.infra.TokenService;
import br.com.luis.partiu.models.Gender;
import br.com.luis.partiu.models.User;
import br.com.luis.partiu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService {


    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    public UserResponseDto register(UserRegisterDto userRegisterDto) {

    if (this.repository.findByEmail(userRegisterDto.email()) != null) {
        throw new RuntimeException("Usuário ja cadastrado");
    }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDto.password());

        User newUser = new User(userRegisterDto);

        newUser.setPassword(encryptedPassword);

        User savedUser = repository.save(newUser);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(),
                savedUser.getAvatarUrl(),savedUser.getGender());
    }

    public String login(UserLoginRequestDto userDto) {

        var token = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());
        var auth = manager.authenticate(token);

        return service.generateToken((User) auth.getPrincipal());
    }

    public UserResponseDto updateUser(UUID id, UserUpdateDto updateDto) {
        User user = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Id não encontrado"));

        user.setAvatarUrl(updateDto.avatarUrl());

        User updatedUser = repository.save(user);

        return new UserResponseDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(),
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
                user.getGender());

    }

    private Page<UserResponseDto> mapUserPage(Page<User> userPage, Pageable pageable) {
        List<UserResponseDto> userList = userPage.getContent().stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getGender()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(userList, pageable, userPage.getTotalElements());
    }


    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
