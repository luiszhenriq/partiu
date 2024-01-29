package br.com.luis.partiu.controller;


import br.com.luis.partiu.dto.user.UserLoginRequestDto;
import br.com.luis.partiu.dto.user.UserRegisterDto;
import br.com.luis.partiu.dto.user.UserResponseDto;
import br.com.luis.partiu.dto.user.UserUpdateDto;
import br.com.luis.partiu.infra.security.TokenJWT;
import br.com.luis.partiu.models.Gender;
import br.com.luis.partiu.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService service;



    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return new ResponseEntity<>(service.register(userRegisterDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenJWT> login(@RequestBody @Valid UserLoginRequestDto userDto) {
        String tokenJWT = service.login(userDto);
        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") UUID id, @RequestBody @Valid UserUpdateDto updateDto) {
        return new ResponseEntity<>(service.updateUser(id, updateDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAll(Pageable pageable) {
        return new ResponseEntity<>(service.getAllUsers(pageable), HttpStatus.OK);
    }

    @GetMapping("/gender")
    public ResponseEntity<Page<UserResponseDto>> getByGender(@RequestParam("gender") Gender gender, Pageable pageable) {
        return new ResponseEntity<>(service.getByGender(gender, pageable), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
