package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.model.notification.Notification;
import org.sopt.smatching.model.notification.NotificationOutput;

import java.util.List;

@Mapper
public interface NotificationMapper {

    // 새로운 알람 생성
    @Insert("INSERT INTO notification(useridx, noticeidx, alerttype, message) " +
            "VALUES(#{notification.userIdx}, #{notification.noticeIdx}, #{notification.alertType}, #{notification.message})")
    @Options(useGeneratedKeys = true, keyProperty = "notification.notificationIdx")
    int save(@Param("notification") final Notification notification);


    // 유저의 모든 알람 내역 가져오기
    @Select("SELECT noticeidx, timestamp AS outputTime, alerttype, message, checked " +
            "FROM notification " +
            "WHERE useridx = #{userIdx} " +
            "ORDER BY notificationidx DESC")
    List<NotificationOutput> findAllByUserIdx(@Param("userIdx") final int userIdx);


    // 사용자의 모든 알람을 읽은 상태로 변경
    @Update("UPDATE notification " +
            "SET checked = 1 " +
            "WHERE useridx = #{userIdx} AND checked = 0")
    void changeToRead(@Param("userIdx") final int userIdx);


    // 읽지않은 사용자 알람 개수 조회
    @Select("SELECT COUNT(notificationidx) " +
            "FROM notification " +
            "WHERE useridx = #{userIdx} AND checked = 0")
    int countUnchecked(@Param("userIdx") final int userIdx);
}
