package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.sopt.smatching.dto.NoticeSummary;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 전체공고 목록 조회
    @Select("SELECT noticeIdx, title, institution, end_date-current_date as dday " +
            "FROM notice " +
            "WHERE valid = 1 " +
            "ORDER BY noticeIdx DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> findAllNoticeSummary(@Param("reqNum") final int reqNum,
                                             @Param("existNum") final int existNum);

    // 전체공고 목록 조회 (스크랩 여부까지)
    @Select("SELECT notice.noticeIdx, notice.title, notice.institution, notice.end_date-current_date as dday, scrap_notice.scrap " +
            "FROM notice " +
            "LEFT OUTER JOIN scrap_notice " +
            "ON notice.noticeIdx = scrap_notice.noticeIdx AND scrap_notice.useridx = #{userIdx} " +
            "WHERE notice.valid = 1 " +
            "ORDER BY notice.noticeIdx DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> findAllNoticeSummaryWithScrap(@Param("reqNum") final int reqNum,
                                                      @Param("existNum") final int existNum,
                                                      @Param("userIdx") final int userIdx);


    // 맞춤공고 목록 조회
    @Select("")
    List<NoticeSummary> findFitNoticeSummary(@Param("reqNum") final int reqNum,
                                             @Param("existNum") final int existNum,
                                             @Param("condIdx") final int condIdx);


    // 맞춤공고 목록 조회 (스크랩 여부까지)
    @Select("")
    List<NoticeSummary> findFitNoticeSummaryWithScrap(@Param("reqNum") final int reqNum,
                                                      @Param("existNum") final int existNum,
                                                      @Param("condIdx") final int condIdx,
                                                      @Param("userIdx") final int userIdx);


    // 공고 상세보기
    @Select("")
    NoticeSummary findNoticeDetailWithNoticeIdx(@Param("noticeIdx") final int noticeIdx);

}
