
package com.sofbecxshield.softbecx.service;

import com.sofbecxshield.softbecx.model.Incidente;
import com.sofbecxshield.softbecx.model.Usuario;
import com.sofbecxshield.softbecx.repository.IncidenteRepository;
import com.sofbecxshield.softbecx.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncidenteService {

    private final IncidenteRepository incidenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final AnalisisRiesgoService analisisRiesgoService;

    public IncidenteService(
            IncidenteRepository incidenteRepository,
            UsuarioRepository usuarioRepository,
            AnalisisRiesgoService analisisRiesgoService) {

        this.incidenteRepository = incidenteRepository;
        this.usuarioRepository = usuarioRepository;
        this.analisisRiesgoService = analisisRiesgoService;
    }

    public List<Incidente> listarIncidentes() {
        return incidenteRepository.findAll();
    }

    public Optional<Incidente> buscarIncidentePorId(Integer id) {
        return incidenteRepository.findById(id);
    }

    @Transactional
    public Incidente guardarIncidente(Incidente incidente) {

        if (incidente.getUsuario() != null) {

            Integer idUsuario =
                    incidente.getUsuario().getIdUsuario();

            if (idUsuario == null) {
                throw new IllegalArgumentException(
                        "El usuario debe tener un identificador.");
            }

            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No existe el usuario con ID: " + idUsuario));

            incidente.setUsuario(usuario);
        }

        Incidente incidenteGuardado =
                incidenteRepository.save(incidente);

        analisisRiesgoService.analizarIncidente(
                incidenteGuardado.getIdIncidente());

        return incidenteGuardado;
    }

    public Incidente actualizarIncidente(
            Integer id, Incidente incidente) {

        Optional<Incidente> incidenteExistente =
                incidenteRepository.findById(id);

        if (incidenteExistente.isPresent()) {

            Incidente incidenteActual = incidenteExistente.get();

            incidenteActual.setTitulo(incidente.getTitulo());
            incidenteActual.setDescripcion(incidente.getDescripcion());
            incidenteActual.setTipoIncidente(incidente.getTipoIncidente());
            incidenteActual.setOrigen(incidente.getOrigen());
            incidenteActual.setEstado(incidente.getEstado());

            if (incidente.getUsuario() == null) {

                incidenteActual.setUsuario(null);

            } else {

                Integer idUsuario =
                        incidente.getUsuario().getIdUsuario();

                if (idUsuario == null) {
                    throw new IllegalArgumentException(
                            "El usuario debe tener un identificador.");
                }

                Usuario usuario = usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "No existe el usuario con ID: " + idUsuario));

                incidenteActual.setUsuario(usuario);
            }

            return incidenteRepository.save(incidenteActual);
        }

        return null;
    }

    public void eliminarIncidente(Integer id) {
        incidenteRepository.deleteById(id);
    }
}
