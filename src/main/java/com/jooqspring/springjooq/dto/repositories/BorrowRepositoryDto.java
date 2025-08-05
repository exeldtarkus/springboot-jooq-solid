package com.jooqspring.springjooq.dto.repositories;

import com.jooqspring.springjooq.dto.QueryParamPaginationDto;
import com.jooqspring.springjooq.entity.Borrow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class BorrowRepositoryDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = false)
    public static class QueryParamSearch extends Borrow {
        Integer limit = 10;
        Integer offset = 0;
        Boolean joinUser;
        Boolean joinBook;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(callSuper = false)
    public static class QueryParamUpdate {
        Long userId;
        Integer bookId;
        String borrowDate;
        String returnDate;
        String status;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class UserResponseSearch extends Borrow {
        private String user_id_alias;
    }

    @Data
    @Builder
    @EqualsAndHashCode(callSuper = false)
    public static class QueryDataUpdate extends Borrow {}

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(callSuper = false)
    public static class QueryDataInsert extends Borrow {
        private transient Long id; // Exclude the 'id' field from serialization
    }

}
