package com.jooqspring.springjooq.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.ZonedDateTime;

/**
 * DTO standar untuk membungkus response API.
 *
 * @param <T> tipe data yang dikembalikan dalam response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseApiResponseDto<T> {
    private T data;
    private boolean success;
    private String message;
    private int status;
    private String requestId;
    private ZonedDateTime timestamp;

    public BaseApiResponseDto(T data, boolean success, String message, int status, String requestId) {
        this.data = data;
        this.success = success;
        this.message = message;
        this.status = status;
        this.requestId = requestId;
        this.timestamp = ZonedDateTime.now();
    }

    // Getters dan Setters

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
