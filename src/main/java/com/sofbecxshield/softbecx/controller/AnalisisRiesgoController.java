
package com.sofbecxshield.softbecx.controller;

import com.sofbecxshield.softbecx.dto.AnalisisRiesgoRespuestaDTO;
import com.sofbecxshield.softbecx.model.AnalisisRiesgo;
import com.sofbecxshield.softbecx.service.AnalisisRiesgoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analisis-riesgo")
public class AnalisisRiesgoController {

    private final AnalisisRiesgoService analisisRiesgoService;

    public AnalisisRiesgoController(
            AnalisisRiesgoService analisisRiesgoService) {
        this.analisisRiesgoService = analisisRiesgoService;
    }

    @GetMapping
    public ResponseEntity<List<AnalisisRiesgoRespuestaDTO>> listarAnalisis() {

        List<AnalisisRiesgoRespuestaDTO> analisis =
                analisisRiesgoService.listarAnalisisRiesgo()
                        .stream()
                        .map(AnalisisRiesgoRespuestaDTO::new)
                        .toList();

        return ResponseEntity.ok(analisis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalisisRiesgoRespuestaDTO> buscarPorId(
            @PathVariable Integer id) {

        return analisisRiesgoService.buscarAnalisisRiesgoPorId(id)
                .map(AnalisisRiesgoRespuestaDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnalisisRiesgoRespuestaDTO> guardar(
            @RequestBody AnalisisRiesgo analisis) {

        AnalisisRiesgo nuevo =
                analisisRiesgoService.guardarAnalisisRiesgo(analisis);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AnalisisRiesgoRespuestaDTO(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnalisisRiesgoRespuestaDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody AnalisisRiesgo analisis) {

        AnalisisRiesgo actualizado =
                analisisRiesgoService.actualizarAnalisisRiesgo(id, analisis);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                new AnalisisRiesgoRespuestaDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        analisisRiesgoService.eliminarAnalisisRiesgo(id);

        return ResponseEntity.noContent().build();
    }
}
