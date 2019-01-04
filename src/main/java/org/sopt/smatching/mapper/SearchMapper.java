package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
            "ORDER BY n.noticeidx DESC")
    List<NoticeSummary> noticeFromEverywhere(@Param("query") final String query);


    // 최근 검색어 조회
    @Select("SELECT query " +
            "")

    // 최근 검색어 저장
    @Insert("INSERT INTO search_log(useridx, query) " +
            "VALUES(#{userIdx}, #{query})")
    int saveQueryLog(@Param("userIdx") final int userIdx, @Param("query") final String query);

    // 최근 검색어 삭제

}
