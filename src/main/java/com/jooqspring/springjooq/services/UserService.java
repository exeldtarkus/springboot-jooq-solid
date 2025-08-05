package com.jooqspring.springjooq.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto;
import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto.UserResponseSearch;
import com.jooqspring.springjooq.entity.User;
import com.jooqspring.springjooq.exceptions.BaseException;
import com.jooqspring.springjooq.interfaces.IUserService;
import com.jooqspring.springjooq.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public List<UserResponseSearch> getAllUsers(UserRepositoryDto.QueryParamSearch filter) {
        return userRepository.findAll(filter);
    }

    public UserResponseSearch getUserById(Long id) {
        UserRepositoryDto.QueryParamSearch params = new UserRepositoryDto.QueryParamSearch();
        params.setId(id);
        return userRepository.findOne(params)
                             .orElseThrow(() -> BaseException.badRequest("User not found!"));
    }

    public int createUser(UserRepositoryDto.QueryDataInsert userDto) {

        UserRepositoryDto.QueryDataInsert insertData = new UserRepositoryDto.QueryDataInsert();
        insertData.setActive(true);
        insertData.setName(userDto.getName());
        insertData.setEmail(userDto.getEmail());

        return userRepository.insert(insertData);
    }

    public int updateUser(Long id, User userDto) {

        UserRepositoryDto.QueryParamUpdate params = new UserRepositoryDto.QueryParamUpdate();
        params.setActive(true);

        UserRepositoryDto.QueryDataUpdate data = new UserRepositoryDto.QueryDataUpdate();
        data.setEmail(null);


        return userRepository.update(data, params);
    }
}
