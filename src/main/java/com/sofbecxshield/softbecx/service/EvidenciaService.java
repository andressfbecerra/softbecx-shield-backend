
package com.sofbecxshield.softbecx.service;

import com.sofbecxshield.softbecx.model.Evidencia;
import com.sofbecxshield.softbecx.model.Incidente;
import com.sofbecxshield.softbecx.repository.EvidenciaRepository;
import com.sofbecxshield.softbecx.repository.IncidenteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EvidenciaService {

    private final EvidenciaRepository evidenciaRepository;
    private final IncidenteRepository incidenteRepository;

    public EvidenciaService(
            EvidenciaRepository evidenciaRepository,
            IncidenteRepository incidenteRepository) {
        this.evidenciaRepository = evidenciaRepository;
        this.incidenteRepository = incidenteRepository;
    }

    public List<Evidencia> listarEvidencias() {
        return evidenciaRepository.findAll();
    }

    public Optional<Evidencia> buscarEvidenciaPorId(Integer id) {
        return evidenciaRepository.findById(id);
    }

    public Evidencia guardarEvidencia(Evidencia evidencia) {
        if (evidencia.getIncidente() == null
                || evidencia.getIncidente().getIdIncidente() == null) {
            throw new IllegalArgumentException(
                    "La evidencia debe estar asociada a un incidente.");
        }

        Integer idIncidente = evidencia.getIncidente().getIdIncidente();

        Incidente incidente = incidenteRepository.findById(idIncidente)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el incidente con ID: " + idIncidente));

        evidencia.setIncidente(incidente);

        return evidenciaRepository.save(evidencia);
    }

    public Evidencia actualizarEvidencia(Integer id, Evidencia evidencia) {
        Optional<Evidencia> evidenciaExistente =
                evidenciaRepository.findById(id);

        if (evidenciaExistente.isPresent()) {
            Evidencia evidenciaActual = evidenciaExistente.get();

            evidenciaActual.setNombreArchivo(evidencia.getNombreArchivo());
            evidenciaActual.setTipoArchivo(evidencia.getTipoArchivo());
            evidenciaActual.setRutaArchivo(evidencia.getRutaArchivo());

            if (evidencia.getIncidente() == null
                    || evidencia.getIncidente().getIdIncidente() == null) {
                throw new IllegalArgumentException(
                        "La evidencia debe estar asociada a un incidente.");
            }

            Integer idIncidente = evidencia.getIncidente().getIdIncidente();

            Incidente incidente = incidenteRepository.findById(idIncidente)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No existe el incidente con ID: " + idIncidente));

            evidenciaActual.setIncidente(incidente);

            return evidenciaRepository.save(evidenciaActual);
        }

        return null;
    }

    public void eliminarEvidencia(Integer id) {
        evidenciaRepository.deleteById(id);
    }
}
