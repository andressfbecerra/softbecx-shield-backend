
package com.sofbecxshield.softbecx.dto;

import com.sofbecxshield.softbecx.model.Evidencia;
import java.time.LocalDateTime;

public class EvidenciaRespuestaDTO {

    private Integer idEvidencia;
    private String nombreArchivo;
    private String tipoArchivo;
    private String rutaArchivo;
    private LocalDateTime fechaCarga;
    private Integer idIncidente;
    private String tituloIncidente;

    public EvidenciaRespuestaDTO() {
    }

    public EvidenciaRespuestaDTO(Evidencia evidencia) {

        this.idEvidencia = evidencia.getIdEvidencia();
        this.nombreArchivo = evidencia.getNombreArchivo();
        this.tipoArchivo = evidencia.getTipoArchivo();
        this.rutaArchivo = evidencia.getRutaArchivo();
        this.fechaCarga = evidencia.getFechaCarga();

        if (evidencia.getIncidente() != null) {
            this.idIncidente =
                    evidencia.getIncidente().getIdIncidente();

            this.tituloIncidente =
                    evidencia.getIncidente().getTitulo();
        }
    }

    public Integer getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(Integer idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public LocalDateTime getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(LocalDateTime fechaCarga) {
        this.fechaCarga = fechaCarga;
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
