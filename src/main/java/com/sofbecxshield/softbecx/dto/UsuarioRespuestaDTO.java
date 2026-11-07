
package com.sofbecxshield.softbecx.dto;

import com.sofbecxshield.softbecx.model.Usuario;
import java.time.LocalDateTime;

public class UsuarioRespuestaDTO {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String rol;
    private Byte estado;
    private LocalDateTime fechaCreacion;

    public UsuarioRespuestaDTO() {
    }

    public UsuarioRespuestaDTO(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.correo = usuario.getCorreo();
        this.rol = usuario.getRol();
        this.estado = usuario.getEstado();
        this.fechaCreacion = usuario.getFechaCreacion();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
