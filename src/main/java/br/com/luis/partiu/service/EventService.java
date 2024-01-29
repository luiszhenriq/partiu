package br.com.luis.partiu.service;
import br.com.luis.partiu.dto.event.DateRangeRequest;
import br.com.luis.partiu.dto.event.EventRequestDto;
import br.com.luis.partiu.dto.event.EventResponseDto;
import br.com.luis.partiu.dto.event.UpdateEventDto;
import br.com.luis.partiu.dto.user.UserViewDto;
import br.com.luis.partiu.infra.exceptions.IdNotFoundException;
import br.com.luis.partiu.models.*;
import br.com.luis.partiu.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class EventService {


    @Autowired
    private EventRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocaleRepository localeRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public EventResponseDto createEvent(EventRequestDto eventDto) {

        User author = userRepository.findById(eventDto.authorId())
                .orElseThrow(() -> new IdNotFoundException("Id do autor não encontrado"));

        Locale locale = localeRepository.findById(eventDto.localeId())
                .orElseThrow(() -> new IdNotFoundException("Id da localidade não encontrado"));

        Category category = categoryRepository.findById(eventDto.categoryId())
                .orElseThrow(() -> new IdNotFoundException("Id da categoria não encontrado"));

        Event newEvent = new Event(eventDto);

        Address newAddress = new Address(eventDto.address());

        newEvent.setAuthor(author);
        newEvent.setLocale(locale);
        newEvent.setCategory(category);

        newEvent.setAddress(newAddress);

        Event saveEvent = repository.save(newEvent);

        return eventResponseDto(saveEvent);
    }

    public Page<EventResponseDto> getAllEventsOrWithFilters(
            Integer fee,
            String city,
            String state,
            String name,
            Pageable pageable) {
        Page<Event> eventPage = repository.findWithFilters(fee, city, state, name, pageable);

        return eventPage.map(this::eventResponseDto);
    }

    public EventResponseDto updateEvent(UUID id, UpdateEventDto updateEventDto) {

        Event event = repository.findById(id).
                orElseThrow(()-> new IdNotFoundException("Id não foi encontrado"));

        event.setTitle(updateEventDto.title());
        event.setDescription(updateEventDto.description());
        event.setCoverUrl(updateEventDto.coverUrl());
        event.setStartAt(updateEventDto.startAt());
        event.setEndsIn(updateEventDto.endsIn());
        event.setFee(updateEventDto.fee());

        Event updatedEvent = repository.save(event);

        return eventResponseDto(updatedEvent);
    }


    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public EventResponseDto getById(UUID id) {
        Event event = repository.findById(id).
                orElseThrow(()-> new IdNotFoundException("Id não foi encontrado"));

        return eventResponseDto(event);

    }

    public Page<EventResponseDto> getEventsByDateRange(DateRangeRequest date, Pageable pageable) {

        Page<Event> eventPage = repository.findEventsByDateRange(date.startAt(), date.endsIn(), pageable);

        return eventPage.map(this::eventResponseDto);

    }

    private EventResponseDto eventResponseDto(Event event) {

        return new EventResponseDto(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCoverUrl(),
                event.getStartAt().format(formatter),
                event.getEndsIn().format(formatter),
                event.getFee(),
                new UserViewDto(
                        event.getAuthor().getId(),
                        event.getAuthor().getName(),
                        event.getAuthor().getEmail(),
                        event.getAuthor().getAvatarUrl(),
                        event.getAuthor().getGender()),
                event.getLocale(),
                event.getCategory(),
                event.getAddress()
        );
    }


}
