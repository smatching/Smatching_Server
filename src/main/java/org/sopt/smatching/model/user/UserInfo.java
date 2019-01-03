package org.sopt.smatching.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

    private String nickname;
    private String profileUrl;
    private int noticeScrapCnt;
    private int talkScrapCnt;
}
