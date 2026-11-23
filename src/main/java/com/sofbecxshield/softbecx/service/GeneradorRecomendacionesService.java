
package com.sofbecxshield.softbecx.service;

import com.sofbecxshield.softbecx.model.Recomendacion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GeneradorRecomendacionesService {

    public List<Recomendacion> generarRecomendaciones(
            List<String> senalesDetectadas) {

        List<Recomendacion> recomendaciones = new ArrayList<>();

        if (senalesDetectadas == null || senalesDetectadas.isEmpty()) {
            agregarRecomendacion(
                    recomendaciones,
                    "Mantener las medidas de seguridad",
                    "Mantener actualizado el sistema, utilizar contraseñas seguras "
                    + "y verificar periódicamente la actividad de las cuentas.",
                    "BAJA"
            );

            return recomendaciones;
        }

        for (String senal : senalesDetectadas) {

            if (senal.contains("información confidencial")
                    || senal.contains("credenciales")) {

                agregarRecomendacion(
                        recomendaciones,
                        "Proteger la información confidencial",
                        "No compartir contraseñas, códigos de verificación "
                        + "ni datos personales mediante mensajes o correos "
                        + "no verificados. Confirmar la identidad del solicitante.",
                        "ALTA"
                );
            }

            if (senal.contains("urgencia o presión")) {

                agregarRecomendacion(
                        recomendaciones,
                        "Verificar los mensajes urgentes",
                        "No actuar apresuradamente ante mensajes que exijan "
                        + "acciones inmediatas. Contactar a la entidad "
                        + "mediante sus canales oficiales.",
                        "ALTA"
                );
            }

            if (senal.contains("remitente o una identidad desconocida")) {

                agregarRecomendacion(
                        recomendaciones,
                        "Verificar la identidad del remitente",
                        "Comprobar la dirección de correo y la identidad "
                        + "del remitente antes de responder o compartir "
                        + "información.",
                        "ALTA"
                );
            }

            if (senal.contains("enlace o dominio sospechoso")) {

                agregarRecomendacion(
                        recomendaciones,
                        "Evitar enlaces sospechosos",
                        "No abrir enlaces desconocidos o sospechosos. "
                        + "Verificar el dominio y acceder a los servicios "
                        + "escribiendo directamente su dirección oficial.",
                        "ALTA"
                );
            }

            if (senal.contains("intentos de acceso no reconocidos")) {

                agregarRecomendacion(
                        recomendaciones,
                        "Proteger el acceso a la cuenta",
                        "Cambiar la contraseña desde el sitio oficial, "
                        + "activar la autenticación multifactor y revisar "
                        + "las sesiones activas de la cuenta.",
                        "ALTA"
                );
            }

            if (senal.contains("posible suplantación")) {

                agregarRecomendacion(
                        recomendaciones,
                        "Verificar posibles suplantaciones",
                        "Confirmar la identidad de la persona o entidad "
                        + "mediante un canal de comunicación oficial. "
                        + "No realizar pagos ni entregar información "
                        + "sin verificar su identidad.",
                        "ALTA"
                );
            }

            if (senal.contains("malware o archivos sospechosos")) {

                agregarRecomendacion(
                        recomendaciones,
                        "Evitar archivos sospechosos",
                        "No descargar ni ejecutar archivos de origen "
                        + "desconocido. Analizar los archivos con una "
                        + "solución de seguridad actualizada.",
                        "ALTA"
                );
            }

            if (senal.contains("Categoría de incidente relacionada")) {

                agregarRecomendacion(
                        recomendaciones,
                        "Revisar el incidente reportado",
                        "Verificar los detalles del incidente y conservar "
                        + "las evidencias disponibles para facilitar "
                        + "su investigación.",
                        "MEDIA"
                );
            }
        }

        if (recomendaciones.isEmpty()) {

            agregarRecomendacion(
                    recomendaciones,
                    "Revisar la actividad reportada",
                    "Verificar la información del incidente y mantener "
                    + "las medidas preventivas de seguridad.",
                    "BAJA"
            );
        }

        return recomendaciones;
    }

    private void agregarRecomendacion(
            List<Recomendacion> recomendaciones,
            String titulo,
            String descripcion,
            String prioridad) {

        Recomendacion recomendacion = new Recomendacion();

        recomendacion.setTitulo(titulo);
        recomendacion.setDescripcion(descripcion);
        recomendacion.setPrioridad(prioridad);

        recomendaciones.add(recomendacion);
    }
}
