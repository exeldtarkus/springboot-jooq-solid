package com.jooqspring.springjooq.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestParamDto {
    private String name;
    private String email;
    private Boolean active;
    private Integer page;
    private Integer size;
}
