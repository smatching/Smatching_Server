package org.sopt.smatching.model.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeDetail {

    private int noticeIdx;

    private String title;
    private String institution;
    private String part;
    private String phone;
    private String origin_url;

    private String reg_date;
    private String start_date;
    private String end_date;

    private String summary;
    private String target;
    private String content;
}
