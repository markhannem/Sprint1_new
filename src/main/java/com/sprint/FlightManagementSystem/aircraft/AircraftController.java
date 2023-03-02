package com.sprint.FlightManagementSystem.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestMapping("aircrafts")
@RestController
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftRepository aircraftRepository;

    // Get all aircraft
    @GetMapping
    public List<Aircraft> getAllAircraft() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable Long id) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        if (aircraft.isPresent()) {
            return aircraft.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aircraft with id: " + id + " not found.");
        }
    }

    // Create a new aircraft
    @PostMapping
    public void createAircraft(@RequestBody Aircraft aircraft) {
        aircraftRepository.save(aircraft);
    }


    // Update an existing aircraft
    @PutMapping(path = "{id}")
    public void updateAircraft(@PathVariable String id, @RequestBody Aircraft aircraft,
                               HttpServletResponse response) {
        Optional<Aircraft> returnValue = aircraftRepository.findById(Long.parseLong(id));
        Aircraft acToUpdate;


        if (returnValue.isPresent()) {
            acToUpdate = returnValue.get();

            acToUpdate.setType(aircraft.getType());
            acToUpdate.setAirlineName(aircraft.getAirlineName());
            acToUpdate.setNumberOfPassengers(aircraft.getNumberOfPassengers());


            aircraftRepository.save(acToUpdate);
        } else {
            try {
                response.sendError(404, "Aircraft with id: " + aircraft.getId() + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // Delete an existing aircraft
    @DeleteMapping(path = "{id}")
    public void deleteAircraft(@PathVariable Long id, HttpServletResponse response) {
        Optional<Aircraft> returnValue = aircraftRepository.findById(id);

        if (returnValue.isPresent()) {
            aircraftRepository.deleteById(id);
        } else {
            try {
                response.sendError(404, "Aircraft with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}/passenger-ids")
    public List<Long> getPassengerIdsByAircraftId(@PathVariable Long id) {
        Optional<Aircraft> aircraft = aircraftRepository.findById(id);
        if (aircraft.isPresent()) {
            return aircraft.get().getPassengerIds();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aircraft with id: " + id + " not found.");
        }
    }

}