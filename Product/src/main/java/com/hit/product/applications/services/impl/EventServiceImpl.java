package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.EventRepository;
import com.hit.product.applications.services.EventService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.EventDto;
import com.hit.product.domains.entities.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        checkEventException(event);
        return event.get();
    }

    @Override
    public Event createEvent(EventDto eventDto) {
        return createOrUpdate(new Event(), eventDto);
    }

    @Override
    public Event updateEvent(Long id, EventDto eventDto) {
        Optional<Event> event = eventRepository.findById(id);
        checkEventException(event);
        return createOrUpdate(event.get(), eventDto);
    }

    private Event createOrUpdate(Event event, EventDto eventDto) {
        modelMapper.map(eventDto, event);
        return eventRepository.save(event);
    }

    @Override
    public TrueFalseResponse deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        checkEventException(event);
        eventRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkEventException(Optional<Event> event) {
        if(event.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
