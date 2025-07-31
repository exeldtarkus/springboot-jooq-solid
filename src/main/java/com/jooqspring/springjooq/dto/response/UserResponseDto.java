package com.jooqspring.springjooq.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
    public String name;
    public String email;
    public Boolean active;
}
