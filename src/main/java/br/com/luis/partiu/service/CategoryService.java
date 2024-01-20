package br.com.luis.partiu.service;


import br.com.luis.partiu.dto.category.CategoryRequestDto;
import br.com.luis.partiu.dto.category.CategoryResponseDto;
import br.com.luis.partiu.models.Category;
import br.com.luis.partiu.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository repository;


    public CategoryResponseDto createCategory(CategoryRequestDto categoryDto) {

        Category newCategory = new Category(categoryDto);

        Category savedCategory = repository.save(newCategory);

        return new CategoryResponseDto(savedCategory.getId(), savedCategory.getName());
    }

    public List<CategoryResponseDto> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(category -> new CategoryResponseDto(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

    public CategoryResponseDto getById(UUID id) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        return new CategoryResponseDto(category.getId(), category.getName());
    }

    public CategoryResponseDto updateCategory(UUID id, CategoryRequestDto categoryDto) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        category.setName(categoryDto.name());

        Category updatedCategory = repository.save(category);

        return new CategoryResponseDto(updatedCategory.getId(), updatedCategory.getName());
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public CategoryResponseDto getByName(String name) {

        Category category = repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Nome não encontrado"));

        return new CategoryResponseDto(category.getId(), category.getName());
    }
}
