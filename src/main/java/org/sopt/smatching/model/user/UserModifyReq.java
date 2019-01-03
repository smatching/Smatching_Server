package org.sopt.smatching.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserModifyReq {

    private String nickname;
    private String email;
    private String password;
    private String profileUrl;

    private String newPassword;
}
