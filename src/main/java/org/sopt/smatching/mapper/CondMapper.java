package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.dto.Cond;
import org.sopt.smatching.dto.CondDetail;
import org.sopt.smatching.dto.UserCond;

import java.util.List;

@Mapper
public interface CondMapper {

    // userIdx로 이름 + 설정해놓은 Cond의 인덱스와 이름 - 0개~2개가 담긴 리스트가 조회됨
    @Select("SELECT user.nickname AS nickName, cond.condidx AS condIdx, cond.condname AS condName " +
            "FROM cond " +
            "INNER JOIN user " +
            "ON cond.userIdx = user.userIdx " +
            "WHERE cond.userIdx = #{userIdx} " +
            "ORDER BY cond.condidx ASC")
    List<UserCond> findInfoByUserIdx(@Param("userIdx") final int userIdx);



    // condIdx로 맞춤조건 조회
    @Select("SELECT * " +
            "FROM cond " +
            "WHERE condidx = #{condIdx}")
    Cond findCondByCondIdx(@Param("condIdx") final int condIdx);



    // 맞춤조건 추가
    @Insert("INSERT INTO cond(useridx, condname, location, age, period, category, field, advantage, busitype, alert) " +
            "VALUES(#{cond.userIdx}, #{cond.condName}, #{cond.location}, #{cond.age}, #{cond.period}, #{cond.category}, #{cond.field}, #{cond.advantage}, #{cond.busiType}, #{cond.alert})")
    @Options(useGeneratedKeys = true, keyProperty = "cond.condIdx")
    // AI값 받기 방법 : 리턴타입 void 로 바꾸고 keyColumn 대신 keyProperty 설정. 인자로 넘겨준 cond 변수에 final 키워드 삭제에. 그러면 알아서 condIdx 채워짐 (세미나 코드는 안먹음)
    void save(@Param("cond") Cond cond);


    
    // 맞춤조건 수정 - alert 는 제외
    @Update("UPDATE cond " +
            "SET condname = #{cond.condname}, location = #{cond.location}, age = #{cond.age}, period = #{cond.period}, category_exc = #{cond.category_exc}, field_inc = #{cond.field_inc}, advantage = #{cond.advantage} " +
            "WHERE condidx = #{cond.condidx}")
    void updateByCondIdx(@Param("cond") final Cond cond);


    // 맞춤조건 삭제
    @Delete("DELETE FROM cond " +
            "where condidx = #{condidx}")
    void deleteByCondIdx(@Param("condidx") final int condidx);
}
