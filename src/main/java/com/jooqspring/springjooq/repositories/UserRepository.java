package com.jooqspring.springjooq.repositories;

import com.jooqspring.springjooq.dto.repositories.UserRepositoryDto;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jooqspring.springjooq.jooq.generated.tables.User.USER;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final DSLContext dsl;

    /**
     * Method pencarian umum dengan kondisi dinamis
     */
    private List<UserRepositoryDto.UserResponseSearch> index(UserRepositoryDto.QueryParamSearch param) {
        var query = dsl.select(USER).from(USER);
        Condition condition = DSL.noCondition();

        if (param.getName() != null) {
            condition = condition.and(USER.NAME.eq(param.getName()));
        }
        if (param.getEmail() != null) {
            condition = condition.and(USER.EMAIL.eq(param.getEmail()));
        }
        if (param.getActive() != null) {
            condition = condition.and(USER.ACTIVE.eq(param.getActive()));
        }
        if (param.getLimit() != null) {
            query.limit(param.getLimit());
        }
        if (param.getOffset() != null) {
            query.offset(param.getOffset());
        }

        return query.where(condition).fetchInto(UserRepositoryDto.UserResponseSearch.class);
    }

    /**
     * Ambil semua data user sesuai filter
     */
    public List<UserRepositoryDto.UserResponseSearch> findAll(UserRepositoryDto.QueryParamSearch param) {
        return index(param);
    }

    /**
     * Ambil satu data user berdasarkan filter (mis. id)
     */
    public Optional<UserRepositoryDto.UserResponseSearch> findOne(UserRepositoryDto.QueryParamSearch param) {
        List<UserRepositoryDto.UserResponseSearch> result = index(param);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    /**
     * Simpan data user baru
     */
    public int insert(UserRepositoryDto.QueryDataInsert data) {
        return dsl.insertInto(USER)
                  .set(USER.ACTIVE, data.getActive())
                  .set(USER.EMAIL, data.getEmail())
                  .set(USER.NAME, data.getName())
                  .execute();
    }

    /**
     * Simpan data user baru multiple rows
     */
    public int[] insertMul(List<UserRepositoryDto.QueryDataInsert> users) {
        var records = users.stream().map(user -> {
            var record = dsl.newRecord(USER);
            record.set(USER.NAME, user.getName());
            record.set(USER.EMAIL, user.getEmail());
            record.set(USER.ACTIVE, user.getActive());
            return record;
        }).collect(Collectors.toList());

        return dsl.batchInsert(records).execute();
    };


    /**
     * Update user berdasarkan ID
     */
    public int update(UserRepositoryDto.QueryDataUpdate data, UserRepositoryDto.QueryParamUpdate params) {
        var query = dsl.update(USER)
            .set(USER.NAME, data.getName())
            .set(USER.EMAIL, data.getEmail());

        Condition condition = DSL.noCondition();

        // Validasi atau kondisi untuk update data
        if (params.getName() != null) {
            condition = condition.and(USER.NAME.eq(params.getName()));
        }
        if (params.getEmail() != null) {
            condition = condition.and(USER.EMAIL.eq(params.getEmail()));
        }
        if (params.getActive() != null) {
            condition = condition.and(USER.ACTIVE.eq(params.getActive()));
        }

        return query.where(condition).execute();
    }

    /**
     * Hapus user berdasarkan ID
     */
    public int delete(Long id) {
        return dsl.deleteFrom(USER)
                .where(USER.ID.eq(id))
                .execute();
    }
}
