package org.sopt.smatching.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.smatching.utils.Age;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class Cond {

    private int condIdx;
    private int userIdx;
    private String condName;
    private long location;
    private int age;
    private int period;
    private int category;
    private long field;
    private int advantage;
    private int busiType;
    private boolean alert;


    // Cond -> CondDetail 생성자
    public Cond(CondDetail condDetail) {
        // ### 이거해야함
    }


    public static long mapToLong(HashMap<String, Boolean> map, String[] arr) {
        // ### 이거해야함
        return 0L;
    }
    public static int mapToInt(HashMap<String, Boolean> map, String[] arr) {
        return (int)mapToLong(map, arr);
    }
}
