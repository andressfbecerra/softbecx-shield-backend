
package com.sofbecxshield.softbecx.service;

import com.sofbecxshield.softbecx.model.Incidente;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class MotorReglasService {

    public ResultadoAnalisis analizar(Incidente incidente) {

        if (incidente == null) {
            throw new IllegalArgumentException(
                    "El incidente no puede ser nulo."
            );
        }

        String texto = normalizar(
                (incidente.getTitulo() == null ? "" : incidente.getTitulo())
                + " "
                + (incidente.getDescripcion() == null
                        ? "" : incidente.getDescripcion())
                + " "
                + (incidente.getTipoIncidente() == null
                        ? "" : incidente.getTipoIncidente())
        );

        int puntaje = 0;
        List<String> senales = new ArrayList<>();

        String tipo = normalizar(
                incidente.getTipoIncidente() == null
                        ? "" : incidente.getTipoIncidente()
        );

        // Regla 1: categoría del incidente.
        if (tipo.contains("phishing")
                || tipo.contains("url")
                || tipo.contains("acceso")
                || tipo.contains("suplantacion")) {

            puntaje += 15;
            senales.add("Categoría de incidente relacionada con una amenaza digital.");
        }

        // Regla 2: mensaje con carácter urgente.
        if (contieneAlguna(texto,
                "urgente",
                "urgencia",
                "tono de urgencia",
                "mensaje urgente",
                "presion para actuar",
                "inmediatamente",
                "de inmediato",
                "ultima oportunidad",
                "hoy mismo",
                "suspension inmediata")) {

            puntaje += 15;
            senales.add("El mensaje utiliza expresiones de urgencia o presión.");
        }

        // Regla 3: solicitud de información confidencial.
        if (contieneAlguna(texto,
                "contrasena",
                "actualizar la contrasena",
                "cambiar la contrasena",
                "clave",
                "credenciales",
                "datos personales",
                "informacion confidencial",
                "informacion bancaria",
                "numero de tarjeta")) {

            puntaje += 25;
            senales.add("Se solicita información confidencial o credenciales.");
        }

        // Regla 4: remitente o identidad desconocida.
        if (contieneAlguna(texto,
                "remitente desconocido",
                "remitente no reconocido",
                "remitente no identificado",
                "no se reconoce al remitente",
                "no se reconoce claramente al remitente",
                "no se reconoce el remitente",
                "correo desconocido",
                "identidad desconocida",
                "persona desconocida",
                "usuario desconocido")) {

            puntaje += 15;
            senales.add("Se identifica un remitente o una identidad desconocida.");
        }

        // Regla 5: enlace o dominio sospechoso.
        boolean enlaceSospechoso = contieneAlguna(texto,
                "enlace desconocido",
                "link desconocido",
                "dominio desconocido",
                "url desconocida",
                "enlace sospechoso",
                "link sospechoso",
                "enlace externo sospechoso",
                "enlace externo no verificado",
                "enlace externo desconocido",
                "enlace no confiable",
                "dominio no verificado",
                "url no verificada");

        boolean enlaceExternoConIndicios = texto.contains("enlace externo")
                && contieneAlguna(texto,
                        "sospechoso",
                        "sospechosa",
                        "sospechas",
                        "no confiable",
                        "no verificado",
                        "no verificada",
                        "desconocido",
                        "desconocida");

        if (enlaceSospechoso || enlaceExternoConIndicios) {
            puntaje += 20;
            senales.add("Se identifica un enlace o dominio sospechoso.");
        }

        // Regla 6: intentos de acceso no reconocidos.
        if (contieneAlguna(texto,
                "acceso no reconocido",
                "intento de acceso",
                "inicio de sesion desconocido",
                "inicio de sesion no reconocido",
                "intentos de acceso no reconocidos")) {

            puntaje += 20;
            senales.add("Se detectan intentos de acceso no reconocidos.");
        }

        // Regla 7: posible suplantación de identidad.
        if (contieneAlguna(texto,
                "suplantacion",
                "se hace pasar por",
                "falso representante",
                "identidad falsa")) {

            puntaje += 15;
            senales.add("Se identifican indicios de posible suplantación.");
        }

        // Regla 8: malware o archivo sospechoso.
        if (contieneAlguna(texto,
                "malware",
                "archivo malicioso",
                "archivo sospechoso",
                "virus",
                "software malicioso",
                "adjunto sospechoso")) {

            puntaje += 20;
            senales.add("Se identifican indicios de malware o archivos sospechosos.");
        }

        // El puntaje máximo permitido es 100.
        puntaje = Math.min(puntaje, 100);

        String nivel;

        if (puntaje >= 80) {
            nivel = "ALTO";
        } else if (puntaje >= 50) {
            nivel = "MEDIO";
        } else {
            nivel = "BAJO";
        }

        String explicacion;

        if (senales.isEmpty()) {
            explicacion = "No se identificaron señales de riesgo "
                    + "mediante las reglas disponibles.";
        } else {
            explicacion = String.join(" ", senales);
        }

        return new ResultadoAnalisis(
                puntaje,
                nivel,
                explicacion,
                senales
        );
    }

    private boolean contieneAlguna(String texto, String... patrones) {

        for (String patron : patrones) {
            if (texto.contains(patron)) {
                return true;
            }
        }

        return false;
    }

    private String normalizar(String texto) {

        String normalizado = Normalizer.normalize(
                texto.toLowerCase(Locale.ROOT),
                Normalizer.Form.NFD
        );

        return normalizado
                .replaceAll("\\p{M}", "")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public static class ResultadoAnalisis {

        private final int puntaje;
        private final String nivel;
        private final String explicacion;
        private final List<String> senalesDetectadas;

        public ResultadoAnalisis(
                int puntaje,
                String nivel,
                String explicacion,
                List<String> senalesDetectadas) {

            this.puntaje = puntaje;
            this.nivel = nivel;
            this.explicacion = explicacion;
            this.senalesDetectadas = new ArrayList<>(senalesDetectadas);
        }

        public int getPuntaje() {
            return puntaje;
        }

        public String getNivel() {
            return nivel;
        }

        public String getExplicacion() {
            return explicacion;
        }

        public List<String> getSenalesDetectadas() {
            return new ArrayList<>(senalesDetectadas);
        }
    }
}
