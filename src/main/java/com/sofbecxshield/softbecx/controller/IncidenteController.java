
package com.sofbecxshield.softbecx.controller;

import com.sofbecxshield.softbecx.dto.IncidenteRespuestaDTO;
import com.sofbecxshield.softbecx.model.Incidente;
import com.sofbecxshield.softbecx.service.IncidenteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incidentes")
public class IncidenteController {

    private final IncidenteService incidenteService;

    public IncidenteController(IncidenteService incidenteService) {
        this.incidenteService = incidenteService;
    }

    @GetMapping
    public ResponseEntity<List<IncidenteRespuestaDTO>> listarIncidentes() {

        List<IncidenteRespuestaDTO> incidentes =
                incidenteService.listarIncidentes()
                        .stream()
                        .map(IncidenteRespuestaDTO::new)
                        .toList();

        return ResponseEntity.ok(incidentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenteRespuestaDTO> buscarIncidentePorId(
            @PathVariable Integer id) {

        return incidenteService.buscarIncidentePorId(id)
                .map(IncidenteRespuestaDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IncidenteRespuestaDTO> guardarIncidente(
            @RequestBody Incidente incidente) {

        Incidente nuevoIncidente =
                incidenteService.guardarIncidente(incidente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new IncidenteRespuestaDTO(nuevoIncidente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidenteRespuestaDTO> actualizarIncidente(
            @PathVariable Integer id,
            @RequestBody Incidente incidente) {

        Incidente actualizado =
                incidenteService.actualizarIncidente(id, incidente);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                new IncidenteRespuestaDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIncidente(
            @PathVariable Integer id) {

        incidenteService.eliminarIncidente(id);

        return ResponseEntity.noContent().build();
    }
}
