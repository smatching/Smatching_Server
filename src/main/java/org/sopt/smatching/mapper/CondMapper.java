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
            "WHERE cond.userIdx = #{userIdx} " +
            "ORDER BY cond.condidx ASC")
    List<UserCond> findInfoByUserIdx(@Param("userIdx") final int userIdx);

    // userIdx로 맞춤조건 알람 설정 여부 조회
    @Select("SELECT SUM(alert) " +
            "FROM cond " +
            "WHERE useridx = #{userIdx}")
    // null 인 경우와 sum이 0 인 경우를 구분해야하기 때문에 wrapper 클래스 사용
    Integer findAlertByUserIdx(@Param("userIdx") final int userIdx);


    // 유저의 맞춤조건의 알람설정 일괄 변경
    @Update("UPDATE cond " +
            "SET alert = #{value} " +
            "WHERE useridx = #{userIdx}")
    int updateAlertByUserIdx(@Param("userIdx") final int userIdx, @Param("value") final int value);


    // condIdx로 맞춤조건 조회
    @Select("SELECT * " +
            "FROM cond " +
            "WHERE condidx = #{condIdx}")
    Cond findCondByCondIdx(@Param("condIdx") final int condIdx);



    // 맞춤조건 추가
    @Insert("INSERT INTO cond(useridx, condname, location, age, period, category, field, advantage, busitype, alert) " +
            "VALUES(#{cond.userIdx}, #{cond.condName}, #{cond.location}, #{cond.age}, #{cond.period}, #{cond.category}, #{cond.field}, #{cond.advantage}, #{cond.busiType}, #{cond.alert})")
    @Options(useGeneratedKeys = true, keyProperty = "cond.condIdx")
    // AI값 받기 방법 : keyColumn 대신 keyProperty 설정 + 인자로 넘겨준 cond 변수에 final 키워드 삭제. 그러면 알아서 condIdx 채워짐 (세미나 코드는 안먹음)
    // 리턴 타입이 int 인 경우 영향받은 row의 개수가 나가는듯
    int save(@Param("cond") Cond cond);



    // 맞춤조건 수정 - INSERT에서 condIdx, userIdx, alert 값은 제외
    @Update("UPDATE cond " +
            "SET condname = #{cond.condName}, location = #{cond.location}, age = #{cond.age}, period = #{cond.period}, category = #{cond.category}, field = #{cond.field}, advantage = #{cond.advantage}, busitype = #{cond.busiType} " +
            "WHERE condidx = #{condIdx} AND useridx = #{userIdx}")
    int updateByCondIdx(@Param("userIdx") final int userIdx, @Param("condIdx") final int condIdx, @Param("cond") final Cond cond);


    // 맞춤조건 삭제
    @Delete("DELETE FROM cond " +
            "WHERE condidx = #{condIdx} AND useridx = #{userIdx}")
    int deleteByCondIdx(@Param("userIdx") final int userIdx, @Param("condIdx") final int condIdx);


    // condIdx로 맞춤조건의 알람여부 조회
    @Select("SELECT alert " +
            "FROM cond " +
            "WHERE condidx = #{condIdx} AND useridx = #{userIdx}")
    Integer findAlert(@Param("userIdx") final int userIdx, @Param("condIdx") final int condIdx);

    // condIdx와 userIdx로 맞춤조건의 알람여부 변경
    @Update("UPDATE cond " +
            "SET alert = #{value} " +
            "WHERE condidx = #{condIdx} AND useridx = #{userIdx}")
    int updateAlert(@Param("userIdx") final int userIdx, @Param("condIdx") final int condIdx, @Param("value") final int value);
}
