
package com.sofbecxshield.softbecx.controller;

import com.sofbecxshield.softbecx.dto.RecomendacionRespuestaDTO;
import com.sofbecxshield.softbecx.model.Recomendacion;
import com.sofbecxshield.softbecx.service.RecomendacionService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recomendaciones")
public class RecomendacionController {

    private final RecomendacionService recomendacionService;

    public RecomendacionController(
            RecomendacionService recomendacionService) {
        this.recomendacionService = recomendacionService;
    }

    @GetMapping
    public ResponseEntity<List<RecomendacionRespuestaDTO>> listarRecomendaciones() {
        List<RecomendacionRespuestaDTO> recomendaciones =
                recomendacionService.listarRecomendaciones()
                        .stream()
                        .map(RecomendacionRespuestaDTO::new)
                        .toList();

        return ResponseEntity.ok(recomendaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionRespuestaDTO> buscarPorId(
            @PathVariable Integer id) {
        return recomendacionService.buscarRecomendacionPorId(id)
                .map(RecomendacionRespuestaDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecomendacionRespuestaDTO> guardar(
            @RequestBody Recomendacion recomendacion) {

        Recomendacion nueva =
                recomendacionService.guardarRecomendacion(recomendacion);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RecomendacionRespuestaDTO(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecomendacionRespuestaDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody Recomendacion recomendacion) {

        Recomendacion actualizada =
                recomendacionService.actualizarRecomendacion(id, recomendacion);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                new RecomendacionRespuestaDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        recomendacionService.eliminarRecomendacion(id);
        return ResponseEntity.noContent().build();
    }
}
