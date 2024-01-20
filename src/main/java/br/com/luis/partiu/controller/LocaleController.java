package br.com.luis.partiu.controller;

import br.com.luis.partiu.dto.locale.LocaleRequestDto;
import br.com.luis.partiu.dto.locale.LocaleResponseDto;
import br.com.luis.partiu.service.LocaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locale")
public class LocaleController {


    @Autowired
    private LocaleService service;


    @PostMapping
    @Transactional
    public ResponseEntity<LocaleResponseDto> createLocale(@RequestBody LocaleRequestDto localeDto) {
        return new ResponseEntity<>(service.createLocale(localeDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LocaleResponseDto>> getAllLocales() {
        return new ResponseEntity<>(service.getAllLocales(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocaleResponseDto> getById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<LocaleResponseDto> updateLocale(@PathVariable("id") UUID id, @RequestBody LocaleRequestDto localeDto) {
        return new ResponseEntity<>(service.updateLocale(id, localeDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
