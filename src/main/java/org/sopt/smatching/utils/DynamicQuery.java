package org.sopt.smatching.utils;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.sopt.smatching.dto.Cond;

public class DynamicQuery {

    // NoticeMapper 에서 사용
    public String findFitNoticeSummaryWithScrap(@Param("cond") final Cond cond) {
        return new SQL() {{

            SELECT("notice.noticeIdx, notice.title, notice.institution, notice.end_date-current_date as dday, scrap_notice.scrap, notice.readcnt");
            FROM("notice");
            LEFT_OUTER_JOIN("scrap_notice ON notice.noticeIdx = scrap_notice.noticeIdx AND scrap_notice.useridx = #{userIdx}");
            WHERE("notice.valid = 1");
            WHERE("notice.notfit = 0");
            ORDER_BY("notice.noticeIdx DESC");

            // 주어진 보기들 중 1개라도 선택한 경우에만 해당 조건에 대한 필터링 적용
            if(cond.getLocation() > 0)
                WHERE("notice.location & #{cond.location} > 0");
            if(cond.getCategory() > 0)
                WHERE("notice.category & #{cond.category} > 0");
            if(cond.getAge() > 0)
                WHERE("notice.age & #{cond.age} > 0");
            if(cond.getPeriod() > 0)
                WHERE("notice.period & #{cond.period} > 0");
            if(cond.getField() > 0)
                WHERE("notice.field & #{cond.field} > 0");
            if(cond.getAdvantage() > 0)
                WHERE("notice.advantage & #{cond.advantage} > 0");
            if(cond.getBusiType() > 0)
                WHERE("notice.busitype & #{cond.busiType} > 0");

        }}.toString() + "\nLIMIT #{existNum}, #{reqNum}"; // LIMIT은 SQL 빌더 클래스에 없는듯
    }

    // NoticeMapper 에서 사용
    public String countFitNotice(@Param("cond") final Cond cond) {
        return new SQL() {{

            SELECT("COUNT(notice.noticeidx)");
            FROM("notice");
            WHERE("notice.valid = 1");
            WHERE("notice.notfit = 0");
            ORDER_BY("notice.noticeIdx DESC");

            // 주어진 보기들 중 1개라도 선택한 경우에만 해당 조건에 대한 필터링 적용
            if(cond.getLocation() > 0)
                WHERE("notice.location & #{cond.location} > 0");
            if(cond.getCategory() > 0)
                WHERE("notice.category & #{cond.category} > 0");
            if(cond.getAge() > 0)
                WHERE("notice.age & #{cond.age} > 0");
            if(cond.getPeriod() > 0)
                WHERE("notice.period & #{cond.period} > 0");
            if(cond.getField() > 0)
                WHERE("notice.field & #{cond.field} > 0");
            if(cond.getAdvantage() > 0)
                WHERE("notice.advantage & #{cond.advantage} > 0");
            if(cond.getBusiType() > 0)
                WHERE("notice.busitype & #{cond.busiType} > 0");

        }}.toString();
    }

}
