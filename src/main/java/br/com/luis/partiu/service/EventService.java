package br.com.luis.partiu.service;


import br.com.luis.partiu.dto.event.EventRequestDto;
import br.com.luis.partiu.dto.event.EventResponseDto;
import br.com.luis.partiu.models.Event;
import br.com.luis.partiu.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EventService {


    @Autowired
    private EventRepository repository;

    public EventResponseDto createEvent(EventRequestDto eventDto) {

        Event newEvent = new Event(eventDto);

        Event saveEvent = repository.save(newEvent);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new  EventResponseDto(saveEvent.getId(),
                saveEvent.getTitle(),
                saveEvent.getDescription(),
                saveEvent.getCoverUrl(),
                saveEvent.getStartAt().format(formatter),
                saveEvent.getEndsIn().format(formatter),
                saveEvent.getFee());
    }
}
