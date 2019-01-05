package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.model.notice.NoticeSummary;

import java.util.List;

@Mapper
public interface SearchMapper {

    // 회원이 전체 지원사업 검색 - scrap 값 포함
    @Select("SELECT n.noticeidx, n.title, n.institution, DATEDIFF(n.end_date, current_date) as dday, n.readcnt, s.scrap " +
            "FROM notice AS n " +
            "INNER JOIN notice_detail AS d " +
            "ON n.noticeidx = d.noticeidx " +
            "LEFT OUTER JOIN scrap_notice AS s " +
            "ON s.useridx = #{userIdx} AND n.noticeidx = s.noticeidx " +
            "WHERE n.valid = 1 AND " +
            "(n.title LIKE CONCAT('%', #{query}, '%') " +
            "OR n.institution LIKE CONCAT('%', #{query}, '%') " +
            "OR d.part LIKE CONCAT('%', #{query}, '%') " +
            "OR d.phone LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_one LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_two LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_three LIKE CONCAT('%', #{query}, '%')) " +
            "ORDER BY n.noticeidx DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> noticeFromEverywhereIncScrap(@Param("userIdx") final int userIdx,
                                                     @Param("query") final String query,
                                                     @Param("reqNum") final int reqNum,
                                                     @Param("existNum") final int existNum);


    // 비회원이 전체 지원사업 검색 - scarp 은 무조건 0 으로 채워짐
    @Select("SELECT n.noticeidx, n.title, n.institution, DATEDIFF(n.end_date, current_date) as dday, n.readcnt " +
            "FROM notice AS n " +
            "INNER JOIN notice_detail AS d " +
            "ON n.noticeidx = d.noticeidx " +
            "WHERE n.valid = 1 AND " +
            "(n.title LIKE CONCAT('%', #{query}, '%') " +
            "OR n.institution LIKE CONCAT('%', #{query}, '%') " +
            "OR d.part LIKE CONCAT('%', #{query}, '%') " +
            "OR d.phone LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_one LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_two LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_three LIKE CONCAT('%', #{query}, '%')) " +
            "ORDER BY n.noticeidx DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> noticeFromEverywhere(@Param("query") final String query,
                                             @Param("reqNum") final int reqNum,
                                             @Param("existNum") final int existNum);


    // 전체 지원사업 검색 결과의 개수만 조회
    @Select("SELECT COUNT(n.noticeidx) " +
            "FROM notice AS n " +
            "INNER JOIN notice_detail AS d " +
            "ON n.noticeidx = d.noticeidx " +
            "WHERE n.valid = 1 AND " +
            "(n.title LIKE CONCAT('%', #{query}, '%') " +
            "OR n.institution LIKE CONCAT('%', #{query}, '%') " +
            "OR d.part LIKE CONCAT('%', #{query}, '%') " +
            "OR d.phone LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_one LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_two LIKE CONCAT('%', #{query}, '%') " +
            "OR d.detail_three LIKE CONCAT('%', #{query}, '%'))")
    int CountNoticeFromEverywhere(@Param("query") final String query);



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
