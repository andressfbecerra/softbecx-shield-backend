
package com.sofbecxshield.softbecx.dto;

import com.sofbecxshield.softbecx.model.Incidente;
import com.sofbecxshield.softbecx.model.Usuario;
import java.time.LocalDateTime;

public class IncidenteRespuestaDTO {

    private Integer idIncidente;
    private String titulo;
    private String descripcion;
    private String tipoIncidente;
    private String origen;
    private String estado;
    private LocalDateTime fechaRegistro;

    private Integer idUsuario;
    private String nombreUsuario;
    private String correoUsuario;

    public IncidenteRespuestaDTO() {
    }

    public IncidenteRespuestaDTO(Incidente incidente) {

        this.idIncidente = incidente.getIdIncidente();
        this.titulo = incidente.getTitulo();
        this.descripcion = incidente.getDescripcion();
        this.tipoIncidente = incidente.getTipoIncidente();
        this.origen = incidente.getOrigen();
        this.estado = incidente.getEstado();
        this.fechaRegistro = incidente.getFechaRegistro();

        Usuario usuario = incidente.getUsuario();

        if (usuario != null) {
            this.idUsuario = usuario.getIdUsuario();
            this.nombreUsuario = usuario.getNombre();
            this.correoUsuario = usuario.getCorreo();
        }
    }

    public Integer getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Integer idIncidente) {
        this.idIncidente = idIncidente;
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

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
}
