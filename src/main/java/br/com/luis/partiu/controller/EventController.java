package br.com.luis.partiu.controller;


import br.com.luis.partiu.dto.event.EventRequestDto;
import br.com.luis.partiu.dto.event.EventResponseDto;
import br.com.luis.partiu.service.EventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventController {


    @Autowired
    private EventService service;


    @PostMapping
    @Transactional
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto eventDto) {
        return new ResponseEntity<>(service.createEvent(eventDto), HttpStatus.CREATED);
    }
}
