package com.jooqspring.springjooq.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jooqspring.springjooq.common.BaseApiResponse;
import com.jooqspring.springjooq.dto.BaseApiResponseDto;
import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto;
import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto.UserResponseSearch;
import com.jooqspring.springjooq.dto.request.UserRequestParamDto;
import com.jooqspring.springjooq.dto.response.UserResponseDto;
import com.jooqspring.springjooq.interfaces.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<BaseApiResponseDto<List<UserResponseDto>>> getAllUsers(UserRequestParamDto params) {

        UserRepositoryDto.QueryParamSearch paramsSearch = new UserRepositoryDto.QueryParamSearch();
        paramsSearch.setActive(params.getActive())
                    .setEmail(params.getEmail())
                    .setLimit(params.getSize())
                    .setOffset(params.getPage());

        List<UserResponseSearch> users = userService.getAllUsers(paramsSearch);

        // transfrom dto repository to dto response API
        List<UserResponseDto> result = users.stream().map(user -> {
            UserResponseDto dto = new UserResponseDto();
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setActive(user.getActive());
            return dto;
        }).toList();

        return BaseApiResponse.success(result, "success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponseDto<UserResponseDto>> getUserById(@PathVariable Long id) {

        UserResponseSearch user = userService.getUserById(id);

        UserResponseDto result = new UserResponseDto();
        result.setName(user.getName());
        result.setEmail(user.getEmail());

        return BaseApiResponse.success(result, "success");
    }
}
