package br.com.luis.partiu.models;


import br.com.luis.partiu.dto.category.CategoryRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(nullable = false)
    private String name;

    public Category(CategoryRequestDto categoryDto) {
        this.name = categoryDto.name();
    }
}
