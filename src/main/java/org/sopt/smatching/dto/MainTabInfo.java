package org.sopt.smatching.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MainTabInfo {

    private List<NoticeSummary> allNoticeSummary;

    private String nickname;
    private int fitNoticeCnt;
    private List<NoticeSummary> fitNoticeSummary;
}