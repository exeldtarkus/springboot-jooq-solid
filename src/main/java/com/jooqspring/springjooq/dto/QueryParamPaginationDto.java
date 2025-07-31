package com.jooqspring.springjooq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QueryParamPaginationDto {
    private Integer limit = 10;
    private Integer offset = 0;
}
