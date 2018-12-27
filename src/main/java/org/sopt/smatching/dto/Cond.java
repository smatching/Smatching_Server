package org.sopt.smatching.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.smatching.utils.enums.Age;
import org.sopt.smatching.utils.enums.Period;

@Getter
@Setter
@NoArgsConstructor
public class Cond {

    private int condidx;
    private int useridx;
    private String condname;
    private long location;
    private Age age;
    private Period period;
    private int category_exc;
    private long field_inc;
    private int advantage;
}
