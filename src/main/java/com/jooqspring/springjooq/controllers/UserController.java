package com.jooqspring.springjooq.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jooqspring.springjooq.common.BaseApiResponse;
import com.jooqspring.springjooq.dto.BaseApiResponseDto;
import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto;
import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto.UserResponseSearch;
import com.jooqspring.springjooq.dto.request.UserRequestBodyDto;
import com.jooqspring.springjooq.dto.request.UserRequestParamDto;
import com.jooqspring.springjooq.dto.response.UserResponseDto;
import com.jooqspring.springjooq.entity.User;
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
        result.setActive(user.getActive());

        return BaseApiResponse.success(result, "success");
    }

    @PostMapping
    public ResponseEntity<BaseApiResponseDto<String>> createUser(@RequestBody UserRequestBodyDto userDto) {
        UserRepositoryDto.QueryDataInsert insertData = new UserRepositoryDto.QueryDataInsert();
        insertData.setActive(true);
        insertData.setName(userDto.getName());
        insertData.setEmail(userDto.getEmail());

        int rowsAffected = userService.createUser(insertData);
        return BaseApiResponse.success("Rows affected: " + rowsAffected, "User created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponseDto<String>> updateUser(@PathVariable Long id,
                                                                 @RequestBody User userDto) {
        int rowsAffected = userService.updateUser(id, userDto);
        return BaseApiResponse.success("Rows affected: " + rowsAffected, "User updated");
    }
}
