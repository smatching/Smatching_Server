package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.sopt.smatching.dto.Cond;
import org.sopt.smatching.dto.NoticeSummary;
import org.sopt.smatching.utils.DynamicQuery;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 전체공고 개수 조회
    @Select("SELECT COUNT(noticeidx) " +
            "FROM notice " +
            "WHERE valid = 1")
    int countAllNotice();

    // 전체공고 목록 조회 (비회원용, 스크랩여부 x)
    @Select("SELECT noticeIdx, title, institution, end_date-current_date as dday, readcnt " +
            "FROM notice " +
            "WHERE valid = 1 " +
            "ORDER BY noticeIdx DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> findAllNoticeSummary(@Param("reqNum") final int reqNum,
                                             @Param("existNum") final int existNum);

    // 전체공고 목록 조회
    @Select("SELECT notice.noticeIdx, notice.title, notice.institution, notice.end_date-current_date as dday, scrap_notice.scrap, notice.readcnt " +
            "FROM notice " +
            "LEFT OUTER JOIN scrap_notice " +
            "ON notice.noticeIdx = scrap_notice.noticeIdx AND scrap_notice.useridx = #{userIdx} " +
            "WHERE notice.valid = 1 " +
            "ORDER BY notice.noticeIdx DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> findAllNoticeSummaryWithScrap(@Param("reqNum") final int reqNum,
                                                      @Param("existNum") final int existNum,
                                                      @Param("userIdx") final int userIdx);

    // 맞춤공고 개수 조회(동적쿼리 사용)
    @SelectProvider(type = DynamicQuery.class, method = "countFitNotice")
    int countFitNotice(@Param("cond") final Cond cond);



    // 맞춤공고 목록 조회(동적쿼리 사용)
    @SelectProvider(type = DynamicQuery.class, method = "findFitNoticeSummaryWithScrap")
    List<NoticeSummary> findFitNoticeSummaryWithScrap(@Param("reqNum") final int reqNum,
                                                      @Param("existNum") final int existNum,
                                                      @Param("userIdx") final int userIdx,
                                                      @Param("cond") final Cond cond);


    // 유저가 스크랩 해놓은 공고 목록 조회
    @Select("SELECT notice.noticeIdx, notice.title, notice.institution, notice.end_date-current_date as dday, scrap_notice.scrap, notice.readcnt " +
            "FROM scrap_notice " +
            "INNER JOIN notice " +
            "ON notice.noticeIdx = scrap_notice.noticeIdx AND notice.valid = 1 " +
            "WHERE scrap_notice.useridx = #{userIdx} " +
            "ORDER BY scrap_notice.timestamp DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> findScrapedNoticeSummary(@Param("userIdx") final int userIdx,
                                                 @Param("reqNum") final int reqNum,
                                                 @Param("existNum") final int existNum);

}
