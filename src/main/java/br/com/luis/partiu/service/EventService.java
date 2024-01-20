package br.com.luis.partiu.service;
import br.com.luis.partiu.dto.event.EventRequestDto;
import br.com.luis.partiu.dto.event.EventResponseDto;
import br.com.luis.partiu.dto.event.UpdateEventDto;
import br.com.luis.partiu.models.Event;
import br.com.luis.partiu.models.User;
import br.com.luis.partiu.repositories.EventRepository;
import br.com.luis.partiu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {


    @Autowired
    private EventRepository repository;

    @Autowired
    private UserRepository userRepository;



    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public EventResponseDto createEvent(EventRequestDto eventDto) {

        User author = userRepository.findById(eventDto.authorId())
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));


        Event newEvent = new Event(eventDto);

        newEvent.setAuthor(author);

        Event saveEvent = repository.save(newEvent);


        return new  EventResponseDto(saveEvent.getId(),
                saveEvent.getTitle(),
                saveEvent.getDescription(),
                saveEvent.getCoverUrl(),
                saveEvent.getStartAt().format(formatter),
                saveEvent.getEndsIn().format(formatter),
                saveEvent.getFee(),
                saveEvent.getAuthor()
                );
    }

   public Page<EventResponseDto> getAllEvents(Pageable pageable) {
        Page<Event> eventPage = repository.findAll(pageable);

        List<EventResponseDto> eventList = eventPage.getContent().stream()
                .map(event -> new EventResponseDto(
                        event.getId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getCoverUrl(),
                        event.getStartAt().format(formatter),
                        event.getEndsIn().format(formatter),
                        event.getFee(),
                        event.getAuthor()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(eventList, pageable, eventPage.getTotalElements());
    }

    public EventResponseDto updateEvent(UUID id, UpdateEventDto updateEventDto) {

        Event event = repository.findById(id).
                orElseThrow(()-> new RuntimeException("Id não foi encontrado"));

        event.setTitle(updateEventDto.title());
        event.setDescription(updateEventDto.description());
        event.setCoverUrl(updateEventDto.coverUrl());
        event.setStartAt(updateEventDto.startAt());
        event.setEndsIn(updateEventDto.endsIn());
        event.setFee(updateEventDto.fee());

        Event updatedEvent = repository.save(event);

        return new  EventResponseDto(updatedEvent.getId(),
                updatedEvent.getTitle(),
                updatedEvent.getDescription(),
                updatedEvent.getCoverUrl(),
                updatedEvent.getStartAt().format(formatter),
                updatedEvent.getEndsIn().format(formatter),
                updatedEvent.getFee(),
                updatedEvent.getAuthor()
        );
    }


    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public EventResponseDto getById(UUID id) {
        Event event = repository.findById(id).
                orElseThrow(()-> new RuntimeException("Id não foi encontrado"));

        return new EventResponseDto(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCoverUrl(),
                event.getStartAt().format(formatter),
                event.getEndsIn().format(formatter),
                event.getFee(),
                event.getAuthor()
               );

    }
}
