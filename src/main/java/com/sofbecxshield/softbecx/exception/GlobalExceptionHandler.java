
package com.sofbecxshield.softbecx.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import jakarta.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarErroresValidacion(
            MethodArgumentNotValidException excepcion) {

        List<String> detalles = excepcion.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "fecha", LocalDateTime.now(),
                        "estado", 400,
                        "error", "Error de validación",
                        "mensaje", "Uno o varios campos contienen datos incorrectos.",
                        "detalles", detalles
                )
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> manejarRestricciones(
            ConstraintViolationException excepcion) {

        List<String> detalles = excepcion.getConstraintViolations()
                .stream()
                .map(error -> error.getPropertyPath() + ": " + error.getMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "fecha", LocalDateTime.now(),
                        "estado", 400,
                        "error", "Error de validación",
                        "mensaje", "Uno o varios campos contienen datos incorrectos.",
                        "detalles", detalles
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> manejarSolicitudIncorrecta(
            IllegalArgumentException excepcion) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "fecha", LocalDateTime.now(),
                        "estado", 400,
                        "error", "Solicitud incorrecta",
                        "mensaje", excepcion.getMessage()
                )
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> manejarFormatoIncorrecto(
            HttpMessageNotReadableException excepcion) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "fecha", LocalDateTime.now(),
                        "estado", 400,
                        "error", "Formato de solicitud incorrecto",
                        "mensaje", "Revisa los datos enviados en el cuerpo de la solicitud."
                )
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> manejarIntegridadDatos(
            DataIntegrityViolationException excepcion) {

        logger.error("Error de integridad de datos: ", excepcion);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        "fecha", LocalDateTime.now(),
                        "estado", 409,
                        "error", "Conflicto con los datos",
                        "mensaje", "No se pudo completar la operación por una restricción de la base de datos."
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarErrorGeneral(
            Exception excepcion) {

        logger.error("Error inesperado al procesar la solicitud: ", excepcion);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of(
                        "fecha", LocalDateTime.now(),
                        "estado", 500,
                        "error", "Error interno del servidor",
                        "mensaje", "Ocurrió un error inesperado al procesar la solicitud."
                )
        );
    }
}
