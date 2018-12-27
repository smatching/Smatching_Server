package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.dto.Cond;

import java.util.List;

@Mapper
public interface CondMapper {

    // userIdx로 맞춤조건 조회 - 0개 or 1개 or 2개가 채워진 리스트 반환
    @Select("SELECT condidx, condname, location, age, period, category_exc, field_inc, advantage " +
            "FROM cond " +
            "WHERE useridx = #{userIdx}")
    List<Cond> findByUserIdx(@Param("userIdx") final int userIdx);


    // 맞춤조건 추가
    @Insert("INSERT INTO cond(useridx, condname, location, age, period, category_exc, field_inc, advantage) " +
            "VALUES(#{cond.useridx}, #{cond.condname}, #{cond.location}, #{cond.age}, #{cond.period}, #{cond.category_exc}, #{cond.field_inc}, #{cond.advantage})")
    @Options(useGeneratedKeys = true, keyColumn = "condIdx")
    void save(@Param("cond") final Cond cond);


    // 맞춤조건 수정
    @Update("UPDATE cond " +
            "SET condname = #{cond.condname}, location = #{cond.location}, age = #{cond.age}, period = #{cond.period}, category_exc = #{cond.category_exc}, field_inc = #{cond.field_inc}, advantage = #{cond.advantage} " +
            "WHERE condidx = #{cond.condidx}")
    void updateByCondIdx(@Param("cond") final Cond cond);


    // 맞춤조건 삭제
    @Delete("DELETE FROM cond " +
            "where condidx = #{condidx}")
    void deleteByCondIdx(@Param("condidx") final int condidx);
}
