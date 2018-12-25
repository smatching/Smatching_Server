package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.dto.User;
import org.sopt.smatching.model.SignUpReq;

@Mapper
public interface UserMapper {

    // 회원 고유 번호로 전체정보 조회
    @Select("SELECT * FROM user WHERE userIdx = #{userIdx}")
    User findByUserIdx(@Param("userIdx") final int userIdx);

    // 회원 고유 번호로 이름만 조회
    @Select("SELECT nickname FROM user WHERE userIdx = #{userIdx}")
    String findNicknameByUserIdx(@Param("userIdx") final int userIdx);

    // 회원 이메일과 비밀번호로 조회
    @Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
    User findByEmailAndPassword(@Param("email") final String email, @Param("password") final String password);

    //회원 등록, Auto Increment는 회원 고유 번호
    @Insert("INSERT INTO user(nickname, email, password) VALUES(#{signUpReq.nickname}, #{signUpReq.email}, #{signUpReq.password})")
    @Options(useGeneratedKeys = true, keyColumn = "userIdx")
    void save(@Param("signUpReq") final SignUpReq signUpReq);
}
