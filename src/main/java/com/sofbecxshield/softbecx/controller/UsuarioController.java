
package com.sofbecxshield.softbecx.controller;

import com.sofbecxshield.softbecx.dto.UsuarioRegistroDTO;
import com.sofbecxshield.softbecx.dto.UsuarioRespuestaDTO;
import com.sofbecxshield.softbecx.service.UsuarioService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRespuestaDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> buscarPorId(
            @PathVariable Integer id) {

        return usuarioService.buscarUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioRespuestaDTO> registrarUsuario(
            @RequestBody UsuarioRegistroDTO datos) {

        UsuarioRespuestaDTO nuevoUsuario =
                usuarioService.guardarUsuario(datos);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody UsuarioRespuestaDTO datos) {

        UsuarioRespuestaDTO actualizado =
                usuarioService.actualizarUsuario(id, datos);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(
            @PathVariable Integer id) {

        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
