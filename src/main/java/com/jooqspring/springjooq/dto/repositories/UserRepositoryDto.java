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
    @EqualsAndHashCode(callSuper = false)
    public static class UserResponseSearch extends User {
        private String emailAlias;
    }

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
    @Builder
    @EqualsAndHashCode(callSuper = false)
    public static class QueryDataUpdate {
        private String name;
        private String email;
        private Boolean active;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(callSuper = false)
    public static class QueryDataInsert {
        private String name;
        private String email;
        private Boolean active;
    }

}
