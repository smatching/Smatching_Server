package org.sopt.smatching.model.cond;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CondRes {

    private String nickname;
    private List<CondSummary> condSummaryList;

    public CondRes(String nickname) {
        this.nickname = nickname;
        this.condSummaryList = new ArrayList<>();
    }
}
