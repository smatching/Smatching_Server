package org.sopt.smatching.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface ScrapMapper {

    // 스크랩 여부 검사
    @Select("SELECT COUNT(scrap) FROM scrap_notice WHERE userIdx = #{userIdx} AND noticeIdx = #{noticeIdx}")
    int isScraped(@Param("userIdx") final int userIdx, @Param("noticeIdx") final int noticeIdx);

    // 스크랩 Row 추가
    @Insert("INSERT INTO scrap_notice (useridx, noticeidx) VALUES(#{userIdx}, #{noticeIdx})")
    int insertScrap(@Param("userIdx") final int userIdx, @Param("noticeIdx") final int noticeIdx);

    // 스크랩 Row 삭제
    @Delete("DELETE FROM scrap_notice WHERE useridx = #{userIdx} AND noticeidx = #{noticeIdx}")
    int deleteScrap(@Param("userIdx") final int userIdx, @Param("noticeIdx") final int noticeIdx);
}
