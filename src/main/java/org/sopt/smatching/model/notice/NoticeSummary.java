package org.sopt.smatching.model.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeSummary {

    private int noticeIdx;
    private String title;
    private String institution;
    private int dday;
    private int scrap;
    private int readCnt;
}
