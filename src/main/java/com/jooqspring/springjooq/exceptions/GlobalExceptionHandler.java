package com.jooqspring.springjooq.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.jooqspring.springjooq.common.BaseApiResponse;
import com.jooqspring.springjooq.dto.BaseApiResponseDto;

/**
 * GlobalExceptionHandler adalah komponen sentral untuk menangani semua exception
 * yang terjadi di controller secara global dan mengubahnya menjadi response API
 * yang konsisten dan terstruktur.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Menangani semua exception turunan dari BaseException.
     * Exception ini biasanya dilempar menggunakan helper seperti:
     * {@code throw BaseException.badRequest("...")}
     *
     * @param ex exception khusus yang telah disesuaikan dengan HTTP status code
     * @return response JSON standar berisi pesan, kode, dan status
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseApiResponseDto<Object>> handleBaseException(BaseException ex) {
        return BaseApiResponse.error(ex.getMessage(), ex.getStatus().value());
    }

    /**
     * Menangani semua jenis exception tak terduga (RuntimeException, NullPointerException, dll)
     * yang belum ditangani oleh handler lainnya.
     *
     * @param ex exception umum yang tidak diketahui
     * @return response error dengan status 500 dan pesan generik
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseApiResponseDto<Object>> handleUnhandled(Exception ex) {
        ex.printStackTrace(); // Logging ke console, bisa diganti dengan logger
        return BaseApiResponse.error("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * Menangani Error router not found
     * yang belum ditangani oleh handler lainnya.
     *
     * @param ex exception router tidak diketahui
     * @return response error dengan status 404 dan pesan generik
     */
    @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseEntity<BaseApiResponseDto<Object>> handleNotFound(NoHandlerFoundException ex) {
            return BaseApiResponse.error("endpoint not found", HttpStatus.NOT_FOUND.value());
        }
    }
