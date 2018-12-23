package org.sopt.smatching.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private int userIdx;
    private String nickname;
    private String email;
    private String password;
    private String profileUrl;
}
