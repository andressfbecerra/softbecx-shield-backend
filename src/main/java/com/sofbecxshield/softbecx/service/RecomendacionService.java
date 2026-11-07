
package com.sofbecxshield.softbecx.service;

import com.sofbecxshield.softbecx.model.AnalisisRiesgo;
import com.sofbecxshield.softbecx.model.Recomendacion;
import com.sofbecxshield.softbecx.repository.AnalisisRiesgoRepository;
import com.sofbecxshield.softbecx.repository.RecomendacionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RecomendacionService {

    private final RecomendacionRepository recomendacionRepository;
    private final AnalisisRiesgoRepository analisisRiesgoRepository;

    public RecomendacionService(
            RecomendacionRepository recomendacionRepository,
            AnalisisRiesgoRepository analisisRiesgoRepository) {
        this.recomendacionRepository = recomendacionRepository;
        this.analisisRiesgoRepository = analisisRiesgoRepository;
    }

    public List<Recomendacion> listarRecomendaciones() {
        return recomendacionRepository.findAll();
    }

    public Optional<Recomendacion> buscarRecomendacionPorId(Integer id) {
        return recomendacionRepository.findById(id);
    }

    public Recomendacion guardarRecomendacion(Recomendacion recomendacion) {
        if (recomendacion.getAnalisisRiesgo() == null
                || recomendacion.getAnalisisRiesgo().getIdAnalisis() == null) {
            throw new IllegalArgumentException(
                    "La recomendación debe estar asociada a un análisis de riesgo.");
        }

        Integer idAnalisis = recomendacion.getAnalisisRiesgo().getIdAnalisis();

        AnalisisRiesgo analisis = analisisRiesgoRepository.findById(idAnalisis)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el análisis de riesgo con ID: " + idAnalisis));

        recomendacion.setAnalisisRiesgo(analisis);

        return recomendacionRepository.save(recomendacion);
    }

    public Recomendacion actualizarRecomendacion(
            Integer id, Recomendacion recomendacion) {

        Optional<Recomendacion> recomendacionExistente =
                recomendacionRepository.findById(id);

        if (recomendacionExistente.isPresent()) {
            Recomendacion recomendacionActual = recomendacionExistente.get();

            recomendacionActual.setTitulo(recomendacion.getTitulo());
            recomendacionActual.setDescripcion(recomendacion.getDescripcion());
            recomendacionActual.setPrioridad(recomendacion.getPrioridad());

            if (recomendacion.getAnalisisRiesgo() == null
                    || recomendacion.getAnalisisRiesgo().getIdAnalisis() == null) {
                throw new IllegalArgumentException(
                        "La recomendación debe estar asociada a un análisis de riesgo.");
            }

            Integer idAnalisis = recomendacion.getAnalisisRiesgo().getIdAnalisis();

            AnalisisRiesgo analisis = analisisRiesgoRepository.findById(idAnalisis)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No existe el análisis de riesgo con ID: " + idAnalisis));

            recomendacionActual.setAnalisisRiesgo(analisis);

            return recomendacionRepository.save(recomendacionActual);
        }

        return null;
    }

    public void eliminarRecomendacion(Integer id) {
        recomendacionRepository.deleteById(id);
    }
}
