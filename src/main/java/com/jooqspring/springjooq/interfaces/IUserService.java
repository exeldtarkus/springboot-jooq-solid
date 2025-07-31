package com.jooqspring.springjooq.interfaces;

import java.util.List;

import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto;
import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto.UserResponseSearch;
import com.jooqspring.springjooq.entity.User;

public interface IUserService {
    List<UserResponseSearch> getAllUsers(UserRepositoryDto.QueryParamSearch filter);
    UserResponseSearch getUserById(Long id);
    int createUser(UserRepositoryDto.QueryDataInsert userDto);
    int updateUser(Long id, User userDto);
}
