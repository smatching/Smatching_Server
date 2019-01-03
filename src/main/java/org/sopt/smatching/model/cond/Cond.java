package org.sopt.smatching.model.cond;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.smatching.utils.MultipleOption;

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


    // Cond 생성자 (CondDetail -> Cond)
    public Cond(CondDetail condDetail) {

        // 안 쓸 변수들 각 type default 값들로 초기화
        this.condIdx = condDetail.getCondIdx();
        this.userIdx = -1;
        this.condName = condDetail.getCondName();
        this.alert = condDetail.isAlert();

        this.location = mapToLong(condDetail.getLocation(), MultipleOption.LOCATIONS);
        this.age = mapToInt(condDetail.getAge(), MultipleOption.AGES);
        this.period = mapToInt(condDetail.getPeriod(), MultipleOption.PERIODS);
        this.field = mapToLong(condDetail.getField(), MultipleOption.FIELDS);
        this.advantage = mapToInt(condDetail.getAdvantage(), MultipleOption.ADVANTAGES);
        this.busiType = mapToInt(condDetail.getBusiType(), MultipleOption.BUSITYPES);

        // DB 저장된 정보는 Include Category(필요한 지원분야)이므로 비트플립 해서 넘김
        int invalidBitCnt = 32 - MultipleOption.CATEGORYS.length;
        int flipped_category = ((~(mapToInt(condDetail.getExcCategory(), MultipleOption.CATEGORYS)) << invalidBitCnt) >>> invalidBitCnt);
        this.category = flipped_category;
    }


    public static long mapToLong(HashMap<String, Boolean> map, String[] arr) {
        long binary = 0L;
        for(int i=arr.length-1; i>=0; i--) {
            binary <<= 1;
            binary += (map.get(arr[i]) ? 1L : 0L);
        }
        return binary;
    }
    public static int mapToInt(HashMap<String, Boolean> map, String[] arr) {
        return (int)mapToLong(map, arr);
    }

}
