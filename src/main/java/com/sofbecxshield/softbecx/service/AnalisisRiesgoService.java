
package com.sofbecxshield.softbecx.service;

import com.sofbecxshield.softbecx.model.AnalisisRiesgo;
import com.sofbecxshield.softbecx.model.Incidente;
import com.sofbecxshield.softbecx.repository.AnalisisRiesgoRepository;
import com.sofbecxshield.softbecx.repository.IncidenteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AnalisisRiesgoService {

    private final AnalisisRiesgoRepository analisisRiesgoRepository;
    private final IncidenteRepository incidenteRepository;

    public AnalisisRiesgoService(
            AnalisisRiesgoRepository analisisRiesgoRepository,
            IncidenteRepository incidenteRepository) {

        this.analisisRiesgoRepository = analisisRiesgoRepository;
        this.incidenteRepository = incidenteRepository;
    }

    public List<AnalisisRiesgo> listarAnalisisRiesgo() {
        return analisisRiesgoRepository.findAll();
    }

    public Optional<AnalisisRiesgo> buscarAnalisisRiesgoPorId(
            Integer id) {

        return analisisRiesgoRepository.findById(id);
    }

    public AnalisisRiesgo guardarAnalisisRiesgo(
            AnalisisRiesgo analisis) {

        if (analisis.getIncidente() == null
                || analisis.getIncidente().getIdIncidente() == null) {

            throw new IllegalArgumentException(
                    "El análisis debe estar asociado a un incidente.");
        }

        Integer idIncidente =
                analisis.getIncidente().getIdIncidente();

        Incidente incidente = incidenteRepository.findById(idIncidente)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el incidente con ID: " + idIncidente));

        analisis.setIncidente(incidente);

        return analisisRiesgoRepository.save(analisis);
    }

    public AnalisisRiesgo actualizarAnalisisRiesgo(
            Integer id, AnalisisRiesgo analisis) {

        Optional<AnalisisRiesgo> analisisExistente =
                analisisRiesgoRepository.findById(id);

        if (analisisExistente.isPresent()) {

            AnalisisRiesgo analisisActual = analisisExistente.get();

            analisisActual.setPuntajeRiesgo(
                    analisis.getPuntajeRiesgo());

            analisisActual.setNivelRiesgo(
                    analisis.getNivelRiesgo());

            analisisActual.setExplicacion(
                    analisis.getExplicacion());

            analisisActual.setMetodoAnalisis(
                    analisis.getMetodoAnalisis());

            if (analisis.getIncidente() == null
                    || analisis.getIncidente().getIdIncidente() == null) {

                throw new IllegalArgumentException(
                        "El análisis debe estar asociado a un incidente.");
            }

            Integer idIncidente =
                    analisis.getIncidente().getIdIncidente();

            Incidente incidente = incidenteRepository.findById(idIncidente)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No existe el incidente con ID: " + idIncidente));

            analisisActual.setIncidente(incidente);

            return analisisRiesgoRepository.save(analisisActual);
        }

        return null;
    }

    public void eliminarAnalisisRiesgo(Integer id) {
        analisisRiesgoRepository.deleteById(id);
    }
}
