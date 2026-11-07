
package com.sofbecxshield.softbecx.service;

import com.sofbecxshield.softbecx.dto.UsuarioRegistroDTO;
import com.sofbecxshield.softbecx.dto.UsuarioRespuestaDTO;
import com.sofbecxshield.softbecx.model.Usuario;
import com.sofbecxshield.softbecx.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioRespuestaDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioRespuestaDTO::new)
                .toList();
    }

    public Optional<UsuarioRespuestaDTO> buscarUsuarioPorId(
            Integer id) {

        return usuarioRepository.findById(id)
                .map(UsuarioRespuestaDTO::new);
    }

    public UsuarioRespuestaDTO guardarUsuario(
            UsuarioRegistroDTO datos) {

        Usuario usuario = new Usuario();

        usuario.setNombre(datos.getNombre());
        usuario.setApellido(datos.getApellido());
        usuario.setCorreo(datos.getCorreo());

        String passwordHash =
                passwordEncoder.encode(datos.getPassword());

        usuario.setPasswordHash(passwordHash);

        usuario.setRol("USUARIO");
        usuario.setEstado((byte) 1);

        Usuario usuarioGuardado =
                usuarioRepository.save(usuario);

        return new UsuarioRespuestaDTO(usuarioGuardado);
    }

    public UsuarioRespuestaDTO actualizarUsuario(
            Integer id, UsuarioRespuestaDTO datos) {

        Optional<Usuario> usuarioExistente =
                usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {

            Usuario usuarioActual = usuarioExistente.get();

            usuarioActual.setNombre(datos.getNombre());
            usuarioActual.setApellido(datos.getApellido());
            usuarioActual.setCorreo(datos.getCorreo());
            usuarioActual.setEstado(datos.getEstado());

            Usuario usuarioActualizado =
                    usuarioRepository.save(usuarioActual);

            return new UsuarioRespuestaDTO(usuarioActualizado);
        }

        return null;
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
