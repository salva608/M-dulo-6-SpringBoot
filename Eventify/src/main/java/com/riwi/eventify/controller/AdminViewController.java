package com.riwi.eventify.controller;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.models.Venue;
import com.riwi.eventify.service.EventService;
import com.riwi.eventify.service.VenueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminViewController {

    private final EventService eventService;
    private final VenueService venueService; // Inyectado y listo para usar

    /**
     * GET: Renderiza el panel principal con la tabla de eventos.
     * Satisface Escenario 1 (Camino Feliz) y Escenario 2 (Lista Vacía).
     */
    @GetMapping("/events")
    public String showDashboard(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "dashboard"; // Redirecciona al archivo src/main/resources/templates/dashboard.html
    }

    /**
     * GET: Muestra el formulario para crear un nuevo evento.
     */
    @GetMapping("/events/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new Event()); // Objeto vacío para el th:object
        return "form-event"; // Redirecciona al archivo form-event.html
    }

    /**
     * POST: Captura los datos del formulario y guarda el evento.
     * Satisface Escenario 3 (Post-Redirect-Get).
     */
    @PostMapping("/events/save")
    public String saveEvent(@ModelAttribute("event") Event event) {
        eventService.save(event);
        // Patrón PRG: Redirecciona al listado para evitar duplicar registros al refrescar
        return "redirect:/admin/events";
    }

    /**
     * GET: Muestra el formulario para crear un nuevo lugar (Venue).
     * Satisface la ruta que el botón "Registrar Nuevo Lugar" está buscando.
     */
    @GetMapping("/venues/new")
    public String showVenueForm(Model model) {
        model.addAttribute("venue", new Venue()); // Objeto vacío para el th:object del formulario de lugares
        return "form-venue"; // Redirecciona al archivo src/main/resources/templates/form-venue.html
    }

    /**
     * POST: Captura los datos del formulario y guarda el lugar (Venue).
     */
    @PostMapping("/venues/save")
    public String saveVenue(@ModelAttribute("venue") Venue venue) {
        venueService.save(venue);
        // Patrón PRG: Redirecciona al panel principal tras guardar con éxito
        return "redirect:/admin/events";
    }
}