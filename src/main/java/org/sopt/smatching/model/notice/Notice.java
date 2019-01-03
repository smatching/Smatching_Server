package org.sopt.smatching.model.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.smatching.model.cond.Cond;
import org.sopt.smatching.model.notice.NoticeInput;
import org.sopt.smatching.utils.MultipleOption;

@Getter
@Setter
@NoArgsConstructor
public class Notice {

    // notice 테이블 부분
    private int noticeIdx;

    private long location;
    private int category;
    private int age;
    private int period;
    private long field;
    private int advantage;
    private int busiType;

    private String title;
    private String institution;
    private String end_date;

    // notice_detail 테이블 부분
    private String reg_date;
    private String start_date;
    private String phoneNum;
    private String refer_url;
    private String origin_url;
    private String part;
    private String detail_one;
    private String detail_two;
    private String detail_three;


    public Notice(NoticeInput noticeInput) {

        this.location = Cond.mapToLong(noticeInput.getLocation(), MultipleOption.LOCATIONS);
        this.age = Cond.mapToInt(noticeInput.getAge(), MultipleOption.AGES);
        this.period = Cond.mapToInt(noticeInput.getPeriod(), MultipleOption.PERIODS);
        this.field = Cond.mapToLong(noticeInput.getField(), MultipleOption.FIELDS);
        this.advantage = Cond.mapToInt(noticeInput.getAdvantage(), MultipleOption.ADVANTAGES);
        this.busiType = Cond.mapToInt(noticeInput.getBusiType(), MultipleOption.BUSITYPES);

        this.title = noticeInput.getTitle();
        this.institution = noticeInput.getInstitution();
        this.end_date = noticeInput.getEnd_date();


        this.reg_date = noticeInput.getReg_date();
        this.start_date = noticeInput.getStart_date();
        this.phoneNum = noticeInput.getPhoneNum();
        this.refer_url = noticeInput.getRefer_url();
        this.origin_url = noticeInput.getOrigin_url();
        this.part = noticeInput.getPart();
        this.detail_one = noticeInput.getDetail_one();
        this.detail_two = noticeInput.getDetail_two();
        this.detail_three = noticeInput.getDetail_three();
    }
}
