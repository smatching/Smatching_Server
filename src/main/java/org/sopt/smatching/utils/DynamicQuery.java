package org.sopt.smatching.utils;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.sopt.smatching.model.cond.Cond;
import org.sopt.smatching.model.notice.Notice;

public class DynamicQuery {

    // NoticeMapper 에서 사용
    public String findFitNoticeSummaryWithScrap(@Param("cond") final Cond cond) {
        return new SQL() {{

            SELECT("notice.noticeIdx, notice.title, notice.institution, DATEDIFF(notice.end_date, current_date) as dday, scrap_notice.scrap, notice.readcnt");
            FROM("notice");
            LEFT_OUTER_JOIN("scrap_notice ON notice.noticeIdx = scrap_notice.noticeIdx AND scrap_notice.useridx = #{userIdx}");
            WHERE("notice.valid = 1");
            WHERE("notice.notfit = 0");
            ORDER_BY("notice.noticeIdx DESC");

            // 필수옵션들
            WHERE("notice.period & #{cond.period} > 0"); // 설립 경과 년수
            WHERE("notice.busitype & #{cond.busiType} > 0"); // 기업형태
            WHERE("notice.field & #{cond.field} > 0"); // 업종
            WHERE("notice.category & #{cond.category} > 0"); // 필요없는 지원사업분야

            // 선택옵션들 - 주어진 보기들 중 1개라도 선택한 경우에만 해당 조건에 대한 필터링 적용
            if(cond.getLocation() > 0)
                WHERE("notice.location & #{cond.location} > 0");
            if(cond.getAge() > 0)
                WHERE("notice.age & #{cond.age} > 0");
//            if(cond.getAdvantage() > 0)
//                WHERE("notice.advantage & #{cond.advantage} > 0");

        }}.toString() + "\nLIMIT #{existNum}, #{reqNum}"; // LIMIT은 SQL 빌더 클래스에 없는듯
    }

    // NoticeMapper 에서 사용
    public String countFitNotice(@Param("cond") final Cond cond) {
        return new SQL() {{

            SELECT("COUNT(notice.noticeidx)");
            FROM("notice");
            WHERE("notice.valid = 1");
            WHERE("notice.notfit = 0");

            // 필수옵션들
            WHERE("notice.period & #{cond.period} > 0"); // 설립 경과 년수
            WHERE("notice.busitype & #{cond.busiType} > 0"); // 기업형태
            WHERE("notice.field & #{cond.field} > 0"); // 업종
            WHERE("notice.category & #{cond.category} > 0"); // 필요없는 지원사업분야

            // 선택옵션들 - 주어진 보기들 중 1개라도 선택한 경우에만 해당 조건에 대한 필터링 적용
            if(cond.getLocation() > 0)
                WHERE("notice.location & #{cond.location} > 0");
            if(cond.getAge() > 0)
                WHERE("notice.age & #{cond.age} > 0");
//            if(cond.getAdvantage() > 0)
//                WHERE("notice.advantage & #{cond.advantage} > 0");

        }}.toString();
    }



    // condMapper 에서 사용
    public String getNotifiedUser(@Param("notice") final Notice notice) {
        return new SQL() {{
            SELECT("DISTINCT cond.useridx");
            FROM("cond");
            WHERE("cond.alert = 1");

            // 필수옵션들
            WHERE("cond.period & #{notice.period} > 0"); // 설립 경과 년수
            WHERE("cond.busitype & #{notice.busiType} > 0"); // 기업형태
            WHERE("cond.field & #{notice.field} > 0"); // 업종
            WHERE("cond.category & #{notice.category} > 0"); // 필요없는 지원사업분야

            // 선택 옵션들
            WHERE("IF(cond.location = 0, " + (int) (Math.pow(2, MultipleOption.LOCATIONS.length) - 1) + ", cond.location) & #{notice.location} > 0");
            WHERE("IF(cond.age = 0, " + (int) (Math.pow(2, MultipleOption.AGES.length) - 1) + ", cond.age) & #{notice.age} > 0");
//            WHERE("IF(cond.advantage = 0, " + (int) (Math.pow(2, MultipleOption.ADVANTAGES.length) - 1) + ", cond.advantage) & #{notice.advantage} > 0");

        }}.toString();
    }
}
