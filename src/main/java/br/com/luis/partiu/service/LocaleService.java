package br.com.luis.partiu.service;


import br.com.luis.partiu.dto.locale.LocaleRequestDto;
import br.com.luis.partiu.dto.locale.LocaleResponseDto;
import br.com.luis.partiu.infra.exceptions.IdNotFoundException;
import br.com.luis.partiu.models.Locale;
import br.com.luis.partiu.repositories.LocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocaleService {

    @Autowired
    private LocaleRepository repository;

    public LocaleResponseDto createLocale(LocaleRequestDto localeDto) {

        Locale newLocale = new Locale(localeDto);
        
        Locale savedLocale = repository.save(newLocale);
        
        return new LocaleResponseDto(savedLocale.getId(), savedLocale.getCity(), savedLocale.getState());
    }

    public List<LocaleResponseDto> getAllLocales() {

        return repository.findAll()
                .stream()
                .map(locale -> new LocaleResponseDto(locale.getId(), locale.getCity(), locale.getState()))
                .collect(Collectors.toList());
    }

    public LocaleResponseDto getById(UUID id) {

        Locale locale = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("id não encontrado"));

        return new LocaleResponseDto(locale.getId(), locale.getCity(), locale.getState());
    }

    public LocaleResponseDto updateLocale(UUID id, LocaleRequestDto localeDto) {

        Locale locale = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("id não encontrado"));

        locale.setCity(localeDto.city());
        locale.setState(localeDto.state());

        Locale savedLocale = repository.save(locale);

        return new LocaleResponseDto(savedLocale.getId(), savedLocale.getCity(), savedLocale.getState());
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
