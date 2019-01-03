package org.sopt.smatching.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class SignUpReq {

    private String nickname;
    private String email;
    private String password;
}
