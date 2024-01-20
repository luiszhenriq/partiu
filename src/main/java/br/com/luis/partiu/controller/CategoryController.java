package br.com.luis.partiu.controller;


import br.com.luis.partiu.dto.category.CategoryRequestDto;
import br.com.luis.partiu.dto.category.CategoryResponseDto;
import br.com.luis.partiu.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


    @Autowired
    private CategoryService service;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryDto) {
        return new ResponseEntity<>(service.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return new ResponseEntity<>(service.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
    @GetMapping("/name")
    public ResponseEntity<CategoryResponseDto> getByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable("id") UUID id, @RequestBody CategoryRequestDto categoryDto) {
        return new ResponseEntity<>(service.updateCategory(id, categoryDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
