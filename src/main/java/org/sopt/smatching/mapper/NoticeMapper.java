package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.sopt.smatching.dto.NoticeSummary;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 공고 목록 전체조회
    @Select("SELECT title, institution, end_date " +
            "FROM notice " +
            "ORDER BY timestamp DESC " +
            "LIMIT #{existNum}, #{reqNum}")
    List<NoticeSummary> findAllNoticeSummary(@Param("reqNum") final int reqNum,
                                             @Param("existNum") final int existNum);

}
