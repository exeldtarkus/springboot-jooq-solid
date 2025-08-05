package com.jooqspring.springjooq.dto.repositories;

import com.jooqspring.springjooq.dto.QueryParamPaginationDto;
import com.jooqspring.springjooq.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class UserRepositoryDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = false)
    public static class QueryParamSearch extends QueryParamPaginationDto {
        private Long id;
        private String name;
        private String email;
        private Boolean active;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(callSuper = false)
    public static class QueryParamUpdate {
        private String name;
        private String email;
        private Boolean active;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class UserResponseSearch extends User {
        private String email_alias;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class QueryDataUpdate extends User {}

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class QueryDataInsert extends User {
        private transient Long id; // Exclude the 'id' field from serialization
    }

}
