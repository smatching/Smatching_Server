package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.model.notification.Notification;
import org.sopt.smatching.model.notification.NotificationOutput;

import java.util.List;

@Mapper
public interface NotificationMapper {

    // 새로운 알람 생성
    @Insert("INSERT INTO notification(useridx, alerttype, message) " +
            "VALUES(#{notification.userIdx}, #{notification.alertType}, #{notification.message})")
    @Options(useGeneratedKeys = true, keyProperty = "notification.notificationIdx")
    int save(@Param("notification") final Notification notification);


    // 유저의 모든 알람 내역 가져오기
    @Select("SELECT notificationidx, timestamp AS outputTime, alerttype, message, checked " +
            "FROM notification " +
            "WHERE useridx = #{userIdx} " +
            "ORDER BY notificationidx DESC")
    List<NotificationOutput> findAllByUserIdx(@Param("userIdx") final int userIdx);


    // 알람 읽은 상태로 변경
    @Update("")
    int changeToRead(@Param("notificationIdx") final int notificationIdx, @Param("userIdx") final int userIdx);
}
