
package com.sofbecxshield.softbecx.dto;

import com.sofbecxshield.softbecx.model.Recomendacion;
import java.time.LocalDateTime;

public class RecomendacionRespuestaDTO {

    private Integer idRecomendacion;
    private String titulo;
    private String descripcion;
    private String prioridad;
    private LocalDateTime fechaCreacion;
    private Integer idAnalisis;
    private String nivelRiesgo;
    private String explicacionAnalisis;

    public RecomendacionRespuestaDTO() {
    }

    public RecomendacionRespuestaDTO(Recomendacion recomendacion) {
        this.idRecomendacion = recomendacion.getIdRecomendacion();
        this.titulo = recomendacion.getTitulo();
        this.descripcion = recomendacion.getDescripcion();
        this.prioridad = recomendacion.getPrioridad();
        this.fechaCreacion = recomendacion.getFechaCreacion();

        if (recomendacion.getAnalisisRiesgo() != null) {
            this.idAnalisis =
                    recomendacion.getAnalisisRiesgo().getIdAnalisis();
            this.nivelRiesgo =
                    recomendacion.getAnalisisRiesgo().getNivelRiesgo();
            this.explicacionAnalisis =
                    recomendacion.getAnalisisRiesgo().getExplicacion();
        }
    }

    public Integer getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Integer idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(Integer idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getExplicacionAnalisis() {
        return explicacionAnalisis;
    }

    public void setExplicacionAnalisis(String explicacionAnalisis) {
        this.explicacionAnalisis = explicacionAnalisis;
    }
}
