package br.com.luis.partiu.controller;


import br.com.luis.partiu.dto.event.DateRangeRequest;
import br.com.luis.partiu.dto.event.EventRequestDto;
import br.com.luis.partiu.dto.event.EventResponseDto;
import br.com.luis.partiu.dto.event.UpdateEventDto;
import br.com.luis.partiu.service.EventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<EventResponseDto>> getAllEventsOrWithFilters(
            Pageable pageable,
            @RequestParam(name = "fee", required = false) Integer fee,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "name", required = false) String name) {
        return new ResponseEntity<>(service.getAllEventsOrWithFilters(fee, city, state, name, pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<Page<EventResponseDto>> getEventsByDateRange (@RequestBody DateRangeRequest date, Pageable pageable) {
        return new ResponseEntity<>(service.getEventsByDateRange(date, pageable), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable("id") UUID id, @RequestBody UpdateEventDto updateEventDto) {
        return new ResponseEntity<>(service.updateEvent(id, updateEventDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
