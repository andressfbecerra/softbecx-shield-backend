
package com.sofbecxshield.softbecx.controller;

import com.sofbecxshield.softbecx.dto.EvidenciaRespuestaDTO;
import com.sofbecxshield.softbecx.model.Evidencia;
import com.sofbecxshield.softbecx.service.EvidenciaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evidencias")
public class EvidenciaController {

    private final EvidenciaService evidenciaService;

    public EvidenciaController(EvidenciaService evidenciaService) {
        this.evidenciaService = evidenciaService;
    }

    @GetMapping
    public ResponseEntity<List<EvidenciaRespuestaDTO>> listarEvidencias() {
        List<EvidenciaRespuestaDTO> evidencias =
                evidenciaService.listarEvidencias()
                        .stream()
                        .map(EvidenciaRespuestaDTO::new)
                        .toList();

        return ResponseEntity.ok(evidencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvidenciaRespuestaDTO> buscarPorId(
            @PathVariable Integer id) {
        return evidenciaService.buscarEvidenciaPorId(id)
                .map(EvidenciaRespuestaDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EvidenciaRespuestaDTO> guardar(
            @RequestBody Evidencia evidencia) {
        Evidencia nueva = evidenciaService.guardarEvidencia(evidencia);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new EvidenciaRespuestaDTO(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvidenciaRespuestaDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody Evidencia evidencia) {

        Evidencia actualizada =
                evidenciaService.actualizarEvidencia(id, evidencia);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                new EvidenciaRespuestaDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        evidenciaService.eliminarEvidencia(id);
        return ResponseEntity.noContent().build();
    }
}
