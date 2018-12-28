package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.dto.Cond;
import org.sopt.smatching.dto.UserCond;

import java.util.List;

@Mapper
public interface CondMapper {

    // userIdx로 이름 + 설정해놓은 Cond의 인덱스와 이름 - 0개~2개가 담긴 리스트가 조회됨
    @Select("SELECT user.nickname AS nickName, cond.condidx AS condIdx, cond.condname AS condName " +
            "FROM cond " +
            "INNER JOIN user " +
            "ON cond.userIdx = user.userIdx " +
            "WHERE cond.userIdx = #{userIdx} ")
    List<UserCond> findInfoByUserIdx(@Param("userIdx") final int userIdx);



    // condIdx로 맞춤조건 조회
    @Select("SELECT * " +
            "FROM cond " +
            "WHERE condidx = #{condIdx}")
    Cond findByCondIdx(@Param("condIdx") final int condIdx);




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
