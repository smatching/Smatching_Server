package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;
import org.sopt.smatching.model.cond.Cond;
import org.sopt.smatching.model.notice.Notice;
import org.sopt.smatching.model.notice.NoticeSummary;
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


    // 새로운 지원사업공고 추가 - notice 테이블
    @Insert("INSERT INTO notice(location, category, age, period, field, advantage, busitype, title, institution, end_date) " +
            "VALUES(#{notice.location}, #{notice.category}, #{notice.age}, #{notice.period}, #{notice.field}, #{notice.advantage}, #{notice.busiType}, #{notice.title}, #{notice.institution}, #{notice.end_date})")
    @Options(useGeneratedKeys = true, keyProperty = "notice.noticeIdx")
    int save(@Param("notice") final Notice notice);


    // 새로운 지원사업공고 추가 - notice_detail 테이블, save메소드 실행 된 직후에 실행되어야함
    @Insert("INSERT INTO notice_detail(noticeidx, reg_date, start_date, phonenum, refer_url, origin_url, part, detail_one, detail_two, detail_three) " +
            "VALUES(#{notice.noticeIdx}, #{notice.reg_date}, #{notice.start_date}, #{notice.phoneNum}, #{notice.refer_url}, #{notice.origin_url}, #{notice.part}, #{notice.detail_one}, #{notice.detail_two}, #{notice.detail_three})")
    int saveDetail(@Param("notice") final Notice notice);

    // 지원사업공고를 기타로 뺌(맞춤지원에 노출되지 않게하고 전체공고에만 노출되게 설정)
    @Update("UPDATE notice " +
            "SET notfit = 1 " +
            "WHERE noticeidx = #{noticeIdx}")
    void makeNotFit(@Param("noticeIdx") final int noticeIdx);


    // 지원사업공고 비활성화
    @Update("UPDATE notice " +
            "SET valid = 0 " +
            "WHERE noticeidx = #{noticeIdx}")
    void invalidate(@Param("noticeIdx") final int noticeIdx);

}
