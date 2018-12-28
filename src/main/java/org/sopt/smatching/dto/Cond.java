package org.sopt.smatching.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.smatching.utils.enums.Age;

@Getter
@Setter
@NoArgsConstructor
public class Cond {

    private int condIdx;
    private int userIdx;
    private String condName;
    private long location;
    private Age age;
    private int period;
    private int category;
    private long field;
    private int advantage;
    private int busiType;
    private boolean alert;
}
