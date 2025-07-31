package com.jooqspring.springjooq.exceptions;

import org.springframework.http.HttpStatus;

/**
 * BaseException untuk mewakili error HTTP secara fleksibel dengan static factory method.
 * Gunakan method seperti {@code BaseException.badRequest("...")} untuk melempar error standar.
 */
public abstract class BaseException extends RuntimeException {
    private final HttpStatus status;

    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }


    // ✅ Inner subclass untuk implementasi static usage
    private static class Index extends BaseException {
        public Index(String message, HttpStatus status) {
            super(message, status);
        }
    }

    /**
     * Lempar exception dengan kode 400 Bad Request.
     * <pre>{@code
     * throw BaseException.badRequest("Data tidak valid");
     * }</pre>
     */
    public static BaseException badRequest(String message) {
        return new Index(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Lempar exception dengan kode 401 Unauthorized.
     * <pre>{@code
     * throw BaseException.unauthorized("Anda belum login");
     * }</pre>
     */
    public static BaseException unauthorized(String message) {
        return new Index(message, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Lempar exception dengan kode 403 Forbidden.
     * <pre>{@code
     * throw BaseException.forbidden("Anda tidak memiliki izin");
     * }</pre>
     */
    public static BaseException forbidden(String message) {
        return new Index(message, HttpStatus.FORBIDDEN);
    }

    /**
     * Lempar exception dengan kode 404 Not Found.
     * <pre>{@code
     * throw BaseException.notFound("User tidak ditemukan");
     * }</pre>
     */
    public static BaseException notFound(String message) {
        return new Index(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Lempar exception dengan kode 409 Conflict.
     * <pre>{@code
     * throw BaseException.conflict("Email sudah digunakan");
     * }</pre>
     */
    public static BaseException conflict(String message) {
        return new Index(message, HttpStatus.CONFLICT);
    }

    /**
     * Lempar exception dengan kode 500 Internal Server Error.
     * <pre>{@code
     * throw BaseException.internalError("Kesalahan sistem");
     * }</pre>
     */
    public static BaseException internalError(String message) {
        return new Index(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
