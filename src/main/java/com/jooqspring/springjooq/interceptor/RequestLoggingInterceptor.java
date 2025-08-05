package com.jooqspring.springjooq.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Buat requestId unik untuk tracing
        String requestId = UUID.randomUUID().toString();

        // Simpan ke MDC agar masuk ke JSON log
        MDC.put("requestId", requestId);

        // Logging ke file (akan otomatis dalam format JSON jika pakai logstash encoder)
        log.debug("Incoming request: {} {}", request.getMethod(), request.getRequestURI());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        log.info("Completed request: {} {} - status={}", request.getMethod(), request.getRequestURI(), response.getStatus());

        // Hapus MDC agar tidak terbawa ke thread lain
        MDC.clear();
    }
}
