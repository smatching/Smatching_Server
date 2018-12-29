package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.sopt.smatching.dto.Cond;
import org.sopt.smatching.dto.NoticeSummary;

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

    // 맞춤공고 개수 조회
    @Select("SELECT COUNT(notice.noticeidx) " +
            "FROM notice " +
            "WHERE notice.valid = 1 " +
            "AND notice.location & #{cond.location} > 0 " +
            "AND notice.category & #{cond.category} > 0 " +
            "AND notice.age & #{cond.age} > 0 " +
            "AND notice.period & #{cond.period} > 0 " +
            "AND notice.field & #{cond.field} > 0 " +
            "AND notice.advantage & #{cond.advantage} > 0 " +
            "AND notice.busitype & #{cond.busiType} > 0")
    int countFitNotice(@Param("cond") final Cond cond);


    // 맞춤공고 목록 조회
    @Select("SELECT notice.noticeIdx, notice.title, notice.institution, notice.end_date-current_date as dday, scrap_notice.scrap, notice.readcnt " +
            "FROM notice " +
            "LEFT OUTER JOIN scrap_notice " +
            "ON notice.noticeIdx = scrap_notice.noticeIdx AND scrap_notice.useridx = #{userIdx} " +
            "WHERE notice.valid = 1 " +
            "AND notice.location & #{cond.location} > 0 " +
            "AND notice.category & #{cond.category} > 0 " +
            "AND notice.age = #{cond.age} " +
            "AND notice.period & #{cond.period} > 0 " +
            "AND notice.field & #{cond.field} > 0 " +
            "AND notice.advantage & #{cond.advantage} > 0 " +
            "AND notice.busitype & #{cond.busiType} > 0 " +
            "ORDER BY notice.noticeIdx DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> findFitNoticeSummaryWithScrap(@Param("reqNum") final int reqNum,
                                                      @Param("existNum") final int existNum,
                                                      @Param("userIdx") final int userIdx,
                                                      @Param("cond") final Cond cond);


}
