package com.riwi.eventify.service;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    private Event validEvent;

    @BeforeEach
    void setUp() {
        validEvent = new Event(1L, "Concierto de Rock", LocalDate.now().plusDays(10), "Gran evento musical");
    }

    @Test
    void save_ValidEvent_ShouldSaveSuccessfully() {
        // Arrange
        when(eventRepository.save(any(Event.class))).thenReturn(validEvent);

        // Act
        Event savedEvent = eventService.save(validEvent);

        // Assert
        assertNotNull(savedEvent);
        assertEquals("Concierto de Rock", savedEvent.getName());
        verify(eventRepository, times(1)).save(validEvent);
    }

    @Test
    void save_EventWithNullName_ShouldThrowException() {
        // Arrange
        Event eventWithNullName = new Event(null, null, LocalDate.now(), "Descripción");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> eventService.save(eventWithNullName)
        );

        assertEquals("El nombre del evento no puede estar vacio", exception.getMessage());
        verify(eventRepository, never()).save(any(Event.class));
    }

    @Test
    void save_EventWithBlankName_ShouldThrowException() {
        // Arrange
        Event eventWithBlankName = new Event(null, "   ", LocalDate.now(), "Descripción");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> eventService.save(eventWithBlankName)
        );

        assertEquals("El nombre del evento no puede estar vacio", exception.getMessage());
        verify(eventRepository, never()).save(any(Event.class));
    }

    @Test
    void save_EventWithNullDate_ShouldThrowException() {
        // Arrange
        Event eventWithNullDate = new Event(null, "Evento Test", null, "Descripción");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> eventService.save(eventWithNullDate)
        );

        assertEquals("La fecha es obligatoria", exception.getMessage());
        verify(eventRepository, never()).save(any(Event.class));
    }

    @Test
    void findAll_ShouldReturnAllEvents() {
        // Arrange
        Event event1 = new Event(1L, "Evento 1", LocalDate.now(), "Descripción 1");
        Event event2 = new Event(2L, "Evento 2", LocalDate.now().plusDays(5), "Descripción 2");
        List<Event> expectedEvents = Arrays.asList(event1, event2);

        when(eventRepository.findAll()).thenReturn(expectedEvents);

        // Act
        List<Event> actualEvents = eventService.findAll();

        // Assert
        assertNotNull(actualEvents);
        assertEquals(2, actualEvents.size());
        assertEquals("Evento 1", actualEvents.get(0).getName());
        assertEquals("Evento 2", actualEvents.get(1).getName());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void findAll_WhenNoEvents_ShouldReturnEmptyList() {
        // Arrange
        when(eventRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Event> actualEvents = eventService.findAll();

        // Assert
        assertNotNull(actualEvents);
        assertTrue(actualEvents.isEmpty());
        verify(eventRepository, times(1)).findAll();
    }
}