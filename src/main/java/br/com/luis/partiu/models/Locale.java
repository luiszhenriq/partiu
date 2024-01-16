package br.com.luis.partiu.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "locale")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Locale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

   private String city;

   private String state;
}
