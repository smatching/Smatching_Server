package org.sopt.smatching.model.cond;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CondSummary {

    private int condIdx;
    private String condName;
    private int noticeCnt;

    public CondSummary(int condIdx, String condName, int noticeCnt) {
        this.condIdx = condIdx;
        this.condName = condName;
        this.noticeCnt = noticeCnt;
    }
}
