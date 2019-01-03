package org.sopt.smatching.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class NoticeInput {

    // notice 테이블 부분
    private HashMap<String, Boolean> location;
    private HashMap<String, Boolean> category;
    private HashMap<String, Boolean> age;
    private HashMap<String, Boolean> period;
    private HashMap<String, Boolean> field;
    private HashMap<String, Boolean> advantage;
    private HashMap<String, Boolean> busiType;
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

    // 기타공고 플래그
    private boolean notfit;
}
