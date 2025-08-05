package com.jooqspring.springjooq.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {
    Long id;
    Long user_id;
    Long book_id;
    String borrow_date;
    String return_date;
    String status;
}
