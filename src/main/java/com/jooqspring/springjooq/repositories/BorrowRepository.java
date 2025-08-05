package com.jooqspring.springjooq.repositories;

import com.jooqspring.springjooq.dto.repositories.BorrowRepositoryDto;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.jooqspring.springjooq.jooq.generated.tables.Borrow.BORROW;
import static com.jooqspring.springjooq.jooq.generated.tables.Book.BOOK;

@Repository
@RequiredArgsConstructor
public class BorrowRepository {

    private final DSLContext dsl;

    /**
     * Method pencarian umum dengan kondisi dinamis
     */
    private List<BorrowRepositoryDto.UserResponseSearch> index(BorrowRepositoryDto.QueryParamSearch param) {
        var query = dsl.select(BORROW).from(BORROW);
        Condition condition = DSL.noCondition();

        if (param.getId() != null) {
            condition = condition.and(BORROW.ID.eq(param.getId()));
        }
        if (param.getUser_id() != null) {
            condition = condition.and(BORROW.USER_ID.eq(param.getUser_id()));
        }
        if (param.getJoinBook() != null) {
            query.join(BOOK).on(BOOK.ID.eq(BORROW.BOOK_ID));
        }
        if (param.getLimit() != null) {
            query.limit(param.getLimit());
        }
        if (param.getOffset() != null) {
            query.offset(param.getOffset());
        }

        return query.where(condition).fetchInto(BorrowRepositoryDto.UserResponseSearch.class);
    }

    /**
     * Ambil semua data user sesuai filter
     */
    public List<BorrowRepositoryDto.UserResponseSearch> findAll(BorrowRepositoryDto.QueryParamSearch param) {
        return index(param);
    }

    /**
     * Ambil satu data user berdasarkan filter (mis. id)
     */
    public Optional<BorrowRepositoryDto.UserResponseSearch> findOne(BorrowRepositoryDto.QueryParamSearch param) {
        List<BorrowRepositoryDto.UserResponseSearch> result = index(param);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    // /**
    //  * Simpan data user baru
    //  */
    // public int insert(BorrowRepositoryDto.QueryDataInsert data) {
    //     return dsl.insertInto(BORROW)
    //               .set(BORROW.ACTIVE, data.getActive())
    //               .set(BORROW.EMAIL, data.getEmail())
    //               .set(BORROW.NAME, data.getName())
    //               .execute();
    // }

    // /**
    //  * Simpan data user baru multiple rows
    //  */
    // public int[] insertMul(List<BorrowRepositoryDto.QueryDataInsert> users) {
    //     var records = users.stream().map(user -> {
    //         var record = dsl.newRecord(BORROW);
    //         record.set(BORROW.NAME, BORROW.getName());
    //         record.set(BORROW.EMAIL, BORROW.getEmail());
    //         record.set(BORROW.ACTIVE, BORROW.getActive());
    //         return record;
    //     }).collect(Collectors.toList());

    //     return dsl.batchInsert(records).execute();
    // };


    // /**
    //  * Update user berdasarkan ID
    //  */
    // public int update(BorrowRepositoryDto.QueryDataUpdate data, BorrowRepositoryDto.QueryParamUpdate params) {
    //     var query = dsl.update(BORROW)
    //         .set(BORROW.BOOK_ID, data.getBook_id())
    //         .set(BORROW.EMAIL, data.getEmail());

    //     Condition condition = DSL.noCondition();

    //     // Validasi atau kondisi untuk update data
    //     if (params.getName() != null) {
    //         condition = condition.and(BORROW.NAME.eq(params.getName()));
    //     }
    //     if (params.getEmail() != null) {
    //         condition = condition.and(BORROW.EMAIL.eq(params.getEmail()));
    //     }
    //     if (params.getActive() != null) {
    //         condition = condition.and(BORROW.ACTIVE.eq(params.getActive()));
    //     }

    //     return query.where(condition).execute();
    // }

    // /**
    //  * Hapus user berdasarkan ID
    //  */
    // public int delete(Long id) {
    //     return dsl.deleteFrom(BORROW)
    //             .where(BORROW.ID.eq(id))
    //             .execute();
    // }
}
