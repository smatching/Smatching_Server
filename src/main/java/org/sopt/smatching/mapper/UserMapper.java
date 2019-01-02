package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.dto.User;
import org.sopt.smatching.dto.UserAlert;
import org.sopt.smatching.dto.UserInfo;
import org.sopt.smatching.model.UserModifyReq;
import org.sopt.smatching.model.SignUpReq;

@Mapper
public interface UserMapper {

    // 회원 이메일과 비밀번호로 userIdx 조회 - 로그인에서 토큰 발급할때 사용
    @Select("SELECT useridx FROM user WHERE email = #{email} AND password = #{password}")
    User findByEmailAndPassword(@Param("email") final String email, @Param("password") final String password);


    // userIdx로 내 정보들 조회 - 회원정보 변경할때 사용
    @Select("SELECT nickname, email, profileurl FROM user WHERE useridx = #{userIdx}")
    UserModifyReq findUserModifyByUserIdx(@Param("userIdx") final int userIdx);


    // 회원정보 변경
    @Update("UPDATE user " +
            "SET nickname = #{userModifyReq.nickname}, password = #{userModifyReq.password} " +
            "WHERE useridx = #{userIdx}")
    int modifyUserByUserIdx(@Param("userIdx") final int userIdx, @Param("userModifyReq") UserModifyReq userModifyReq);


    // userIdx와 입력 비밀번호로 비밀번호 일치여부 확인 - 1=true / 0=false
    @Select("SELECT IF(password = #{inputPassword}, 1, 0 ) FROM user WHERE useridx = #{userIdx}")
    int checkPassword(@Param("userIdx") final int userIdx, @Param("inputPassword") final String inputPassword);


    // 프로필 사진 변경
    @Update("UPDATE user " +
            "SET profileurl = #{profileUrl} " +
            "WHERE useridx = #{userIdx}")
    void updateProfileUrlByUserIdx(@Param("userIdx") final int userIdx, @Param("profileUrl") final String profileUrl);


    // 프로필 사진 삭제
    @Update("UPDATE user " +
            "SET profileurl = NULL " +
            "WHERE useridx = #{userIdx}")
    void deleteProfileUrlByUserIdx(@Param("userIdx") final int userIdx);


    // 회원탈퇴 - 이메일 앞에 타임스탬프를 붙여서 탈퇴한 회원으로 처리
    @Update("UPDATE user " +
            "SET email = CONCAT(#{timestamp}, email) " +
            "WHERE useridx = #{userIdx}")
    int discardByUserIdx(@Param("userIdx") final int userIdx, @Param("timestamp") final String timestamp);


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


    // 유저의 알람설정 여부 조회 (마이페이지 설정 탭에서 사용)
    @Select("SELECT user.talkalert AS talkAlert, SUM(cond.alert) AS condAlert " +
            "FROM user " +
            "LEFT OUTER JOIN cond " +
            "ON user.useridx = cond.useridx " +
            "WHERE user.useridx = #{userIdx} " +
            "GROUP BY user.useridx")
    UserAlert findUserAlertByUserIdx(@Param("userIdx") final int userIdx);


    // 유저의 창업토크 알람설정 여부만 조회
    @Select("SELECT talkalert " +
            "FROM user " +
            "WHERE useridx = #{userIdx}")
    int findTalkAlertByUserIdx(@Param("userIdx") final int userIdx);


    // 유저의 창업토크 알람설정 여부 변경
    @Update("UPDATE user " +
            "SET talkalert = #{value} " +
            "WHERE useridx = #{userIdx}")
    int updateTalkAlertByUserIdx(@Param("userIdx") final int userIdx, @Param("value") final int value);
}
