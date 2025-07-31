package com.jooqspring.springjooq.common;

import org.springframework.http.ResponseEntity;

import com.jooqspring.springjooq.dto.BaseApiResponseDto;

/**
 * Utility class untuk membungkus response API dalam format standar {@link BaseApiResponseDto}.
 * Umumnya digunakan untuk menghasilkan response JSON yang seragam untuk success dan error.
 */
public class BaseApiResponse {

    /**
     * Mengembalikan response sukses dengan data dan pesan custom.
     *
     * @param data    data hasil response
     * @param message pesan sukses
     * @param <T>     tipe data yang dikembalikan
     * @return ResponseEntity dengan struktur BaseApiResponseDto dan status 200
     */
    public static <T> ResponseEntity<BaseApiResponseDto<T>> success(T data, String message) {
        return ResponseEntity.ok(new BaseApiResponseDto<>(
                data,
                true,
                message,
                200,
                null
        ));
    }

    /**
     * Mengembalikan response sukses default dengan data dan pesan "Success".
     *
     * @param data data hasil response
     * @param <T>  tipe data yang dikembalikan
     * @return ResponseEntity dengan struktur BaseApiResponseDto dan status 200
     */
    public static <T> ResponseEntity<BaseApiResponseDto<T>> success(T data) {
        return success(data, "Success");
    }

    /**
     * Mengembalikan response error dengan status dan pesan tertentu.
     *
     * @param message     pesan error yang akan ditampilkan
     * @param statusCode  kode HTTP status (misalnya 400, 404, 500)
     * @return ResponseEntity dengan struktur BaseApiResponseDto dan status sesuai parameter
     */
    public static ResponseEntity<BaseApiResponseDto<Object>> error(String message, int statusCode) {
        return ResponseEntity.status(statusCode).body(new BaseApiResponseDto<>(
                null,
                false,
                message,
                statusCode,
                null
        ));
    }
}
