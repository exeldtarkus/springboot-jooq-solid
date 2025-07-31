package com.jooqspring.springjooq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryParamDto extends QueryParamPaginationDto {
    private Long id;
    private String username;
    private String email;
    private Boolean active;

    // hanya constructor tambahan untuk dengan pagination
    public UserQueryParamDto(Long id, String username, String email, Boolean active, Integer limit, Integer offset) {
        super(limit, offset);
        this.id = id;
        this.username = username;
        this.email = email;
        this.active = active;
    }
}
