package br.com.luis.partiu.models;


import br.com.luis.partiu.dto.user.UserRegisterDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(UserRegisterDto userRegisterDto) {
        this.name = userRegisterDto.name();
        this.email = userRegisterDto.email();
        this.password = userRegisterDto.password();
        this.avatarUrl = userRegisterDto.avatarUrl();
        this.gender = userRegisterDto.gender();

    }
}
