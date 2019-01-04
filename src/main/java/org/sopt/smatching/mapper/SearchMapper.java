package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.model.notice.NoticeSummary;

import java.util.List;

@Mapper
public interface SearchMapper {

    // 지원사업에서 검색 - dday, scarp, readCnt는 무조건 0 으로 채워짐
    @Select("SELECT n.noticeidx, n.title, n.institution " +
            "FROM notice AS n " +
            "INNER JOIN notice_detail AS d " +
            "ON n.noticeidx = d.noticeidx " +
            "WHERE n.title LIKE CONCAT('%', #{query}, '%') " +
            "OR n.institution LIKE CONCAT('%', #{query}, '%') " +
            "OR d.part LIKE CONCAT('%', #{query}, '%') " +
            "OR d.phone LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_one LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_two LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_three LIKE CONCAT('%', #{query}, '%') " +
            "ORDER BY n.noticeidx DESC " +
            "LIMIT 0, 4")
    List<NoticeSummary> noticeFromEverywhere(@Param("query") final String query);


    // 최근 검색어 저장
    @Insert("INSERT INTO search_log(useridx, query) " +
            "VALUES(#{userIdx}, #{query})")
    int saveQueryLog(@Param("userIdx") final int userIdx, @Param("query") final String query);


    // 최근 검색어 조회
    @Select("SELECT query " +
            "FROM search_log " +
            "WHERE useridx = #{userIdx} " +
            "ORDER BY searchlogidx DESC")
    List<String> findQueryLogsByUserIdx(@Param("userIdx") final int userIdx);


    // 최근 검색어 삭제를 위해 먼저 조회
    @Select("SELECT searchlogidx " +
            "FROM search_log " +
            "WHERE useridx = #{userIdx} " +
            "ORDER BY searchlogidx DESC " +
            "LIMIT #{idx}, 1")
    int findSearchLogIdx(@Param("userIdx") final int userIdx, @Param("idx") final int idx);


    // 최근검색어 삭제
    @Delete("DELETE FROM search_log " +
            "WHERE searchlogidx = #{searchLogIdx}")
    int deleteBySearchLogIdx(@Param("searchLogIdx") final int searchLogIdx);

}
