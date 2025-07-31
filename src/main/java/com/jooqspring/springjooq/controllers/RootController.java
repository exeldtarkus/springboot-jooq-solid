package com.jooqspring.springjooq.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jooqspring.springjooq.common.BaseApiResponse;
import com.jooqspring.springjooq.dto.BaseApiResponseDto;


/**
 * RootController handles the root endpoint of the application.
 * It returns a success response with a message indicating the service name.
 */
@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<BaseApiResponseDto<Object>> index() {
        return BaseApiResponse.success(null, "ms-user-jooq");
    }
}
