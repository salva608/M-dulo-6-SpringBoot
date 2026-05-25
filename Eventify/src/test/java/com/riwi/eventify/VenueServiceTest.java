package com.riwi.eventify.service;

import com.riwi.eventify.models.Venue;
import com.riwi.eventify.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VenueServiceTest {

    @Mock
    private VenueRepository venueRepository;

    @InjectMocks
    private VenueService venueService;

    private Venue validVenue;

    @BeforeEach
    void setUp() {
        validVenue = new Venue(1L, "Auditorio Nacional", "Calle 45 #123", 500);
    }

    @Test
    void save_ValidVenue_ShouldSaveSuccessfully() {
        // Arrange
        when(venueRepository.save(any(Venue.class))).thenReturn(validVenue);

        // Act
        Venue savedVenue = venueService.save(validVenue);

        // Assert
        assertNotNull(savedVenue);
        assertEquals("Auditorio Nacional", savedVenue.getName());
        assertEquals("Calle 45 #123", savedVenue.getAddress());
        assertEquals(500, savedVenue.getCapacity());
        verify(venueRepository, times(1)).save(validVenue);
    }

    @Test
    void save_VenueWithNullName_ShouldThrowException() {
        // Arrange
        Venue venueWithNullName = new Venue(null, null, "Calle 123", 100);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> venueService.save(venueWithNullName)
        );

        assertEquals("El nombre del lugar no puede estar vacio", exception.getMessage());
        verify(venueRepository, never()).save(any(Venue.class));
    }

    @Test
    void save_VenueWithBlankName_ShouldThrowException() {
        // Arrange
        Venue venueWithBlankName = new Venue(null, "   ", "Calle 123", 100);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> venueService.save(venueWithBlankName)
        );

        assertEquals("El nombre del lugar no puede estar vacio", exception.getMessage());
        verify(venueRepository, never()).save(any(Venue.class));
    }

    @Test
    void save_VenueWithNullAddress_ShouldThrowException() {
        // Arrange
        Venue venueWithNullAddress = new Venue(null, "Venue Test", null, 100);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> venueService.save(venueWithNullAddress)
        );

        assertEquals("La direccion no puede quedar vacia", exception.getMessage());
        verify(venueRepository, never()).save(any(Venue.class));
    }

    @Test
    void save_VenueWithBlankAddress_ShouldThrowException() {
        // Arrange
        Venue venueWithBlankAddress = new Venue(null, "Venue Test", "   ", 100);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> venueService.save(venueWithBlankAddress)
        );

        assertEquals("La direccion no puede quedar vacia", exception.getMessage());
        verify(venueRepository, never()).save(any(Venue.class));
    }

    @Test
    void save_VenueWithZeroCapacity_ShouldThrowException() {
        // Arrange
        Venue venueWithZeroCapacity = new Venue(null, "Venue Test", "Calle 123", 0);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> venueService.save(venueWithZeroCapacity)
        );

        assertEquals("La capacidad debe ser mayor de 0", exception.getMessage());
        verify(venueRepository, never()).save(any(Venue.class));
    }

    @Test
    void save_VenueWithNegativeCapacity_ShouldThrowException() {
        // Arrange
        Venue venueWithNegativeCapacity = new Venue(null, "Venue Test", "Calle 123", -50);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> venueService.save(venueWithNegativeCapacity)
        );

        assertEquals("La capacidad debe ser mayor de 0", exception.getMessage());
        verify(venueRepository, never()).save(any(Venue.class));
    }

    @Test
    void findAll_ShouldReturnAllVenues() {
        // Arrange
        Venue venue1 = new Venue(1L, "Lugar 1", "Dirección 1", 100);
        Venue venue2 = new Venue(2L, "Lugar 2", "Dirección 2", 200);
        List<Venue> expectedVenues = Arrays.asList(venue1, venue2);

        when(venueRepository.findAll()).thenReturn(expectedVenues);

        // Act
        List<Venue> actualVenues = venueService.findAll();

        // Assert
        assertNotNull(actualVenues);
        assertEquals(2, actualVenues.size());
        assertEquals("Lugar 1", actualVenues.get(0).getName());
        assertEquals("Lugar 2", actualVenues.get(1).getName());
        verify(venueRepository, times(1)).findAll();
    }

    @Test
    void findAll_WhenNoVenues_ShouldReturnEmptyList() {
        // Arrange
        when(venueRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Venue> actualVenues = venueService.findAll();

        // Assert
        assertNotNull(actualVenues);
        assertTrue(actualVenues.isEmpty());
        verify(venueRepository, times(1)).findAll();
    }
}