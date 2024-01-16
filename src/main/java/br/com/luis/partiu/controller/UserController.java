package br.com.luis.partiu.controller;


import br.com.luis.partiu.dto.user.UserRegisterDto;
import br.com.luis.partiu.dto.user.UserResponseDto;
import br.com.luis.partiu.dto.user.UserUpdateDto;
import br.com.luis.partiu.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService service;


    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        return new ResponseEntity<>(service.register(userRegisterDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") UUID id, @RequestBody UserUpdateDto updateDto) {
        return new ResponseEntity<>(service.updateUser(id, updateDto), HttpStatus.OK);
    }
}
