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
            "ORDER BY timestamp DESC, noticeIdx DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> findAllNoticeSummary(@Param("reqNum") final int reqNum,
                                             @Param("existNum") final int existNum);


    // 맞춤공고 목록 조회 <- notice 테이블과 cond 테이블 조인 필요... 이 mapper에 구현하는거 맞나??


    // 공고 상세보기 <- notice 테이블과 notice_detail 테이블 조인 필요...
}
