
package com.sofbecxshield.softbecx.dto;

import com.sofbecxshield.softbecx.model.AnalisisRiesgo;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AnalisisRiesgoRespuestaDTO {

    private Integer idAnalisis;
    private BigDecimal puntajeRiesgo;
    private String nivelRiesgo;
    private String explicacion;
    private String metodoAnalisis;
    private LocalDateTime fechaAnalisis;
    private Integer idIncidente;
    private String tituloIncidente;

    public AnalisisRiesgoRespuestaDTO() {
    }

    public AnalisisRiesgoRespuestaDTO(AnalisisRiesgo analisis) {

        this.idAnalisis = analisis.getIdAnalisis();
        this.puntajeRiesgo = analisis.getPuntajeRiesgo();
        this.nivelRiesgo = analisis.getNivelRiesgo();
        this.explicacion = analisis.getExplicacion();
        this.metodoAnalisis = analisis.getMetodoAnalisis();
        this.fechaAnalisis = analisis.getFechaAnalisis();

        if (analisis.getIncidente() != null) {
            this.idIncidente =
                    analisis.getIncidente().getIdIncidente();

            this.tituloIncidente =
                    analisis.getIncidente().getTitulo();
        }
    }

    public Integer getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(Integer idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public BigDecimal getPuntajeRiesgo() {
        return puntajeRiesgo;
    }

    public void setPuntajeRiesgo(BigDecimal puntajeRiesgo) {
        this.puntajeRiesgo = puntajeRiesgo;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public String getMetodoAnalisis() {
        return metodoAnalisis;
    }

    public void setMetodoAnalisis(String metodoAnalisis) {
        this.metodoAnalisis = metodoAnalisis;
    }

    public LocalDateTime getFechaAnalisis() {
        return fechaAnalisis;
    }

    public void setFechaAnalisis(LocalDateTime fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    public Integer getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Integer idIncidente) {
        this.idIncidente = idIncidente;
    }

    public String getTituloIncidente() {
        return tituloIncidente;
    }

    public void setTituloIncidente(String tituloIncidente) {
        this.tituloIncidente = tituloIncidente;
    }
}
