package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.dto.User;
import org.sopt.smatching.dto.UserInfo;
import org.sopt.smatching.model.SignUpReq;

@Mapper
public interface UserMapper {

    // 회원 이메일과 비밀번호로 조회
    @Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
    User findByEmailAndPassword(@Param("email") final String email, @Param("password") final String password);

    // 회원 추가
    @Insert("INSERT INTO user(nickname, email, password) VALUES(#{signUpReq.nickname}, #{signUpReq.email}, #{signUpReq.password})")
    @Options(useGeneratedKeys = true, keyColumn = "userIdx")
    void save(@Param("signUpReq") final SignUpReq signUpReq);

    // 마이페이지 메인에 필요한 정보 조회
    @Select("SELECT user.nickname, user.profileurl, COUNT(scrap) AS noticeScrapCnt " +
            "FROM user " +
            "LEFT OUTER JOIN scrap_notice " +
            "ON user.useridx = scrap_notice.useridx " +
            "WHERE user.useridx = #{userIdx} " +
            "GROUP BY user.useridx")
    UserInfo findUserInfoByUserIdx(@Param("userIdx") final int userIdx);
}
