package org.sopt.smatching.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.smatching.utils.MultipleOption;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class CondDetail {

    private int condIdx;
    private String condName;
    private boolean alert;

    private HashMap<String, Boolean> location;
    private HashMap<String, Boolean> age;
    private HashMap<String, Boolean> period;
    private HashMap<String, Boolean> field;
    private HashMap<String, Boolean> advantage;
    private HashMap<String, Boolean> busiType;

    private HashMap<String, Boolean> excCategory;


    // CondDetail 생성자 (Cond -> CondDetail)
    public CondDetail(Cond cond) {

        this.condIdx = cond.getCondIdx();
        this.condName = cond.getCondName();
        this.alert = cond.isAlert();

        this.location = bitToMap(cond.getLocation(), MultipleOption.LOCATIONS);
        this.age = bitToMap(cond.getAge(), MultipleOption.AGES);
        this.period = bitToMap(cond.getPeriod(), MultipleOption.PERIODS);
        this.field = bitToMap(cond.getField(), MultipleOption.FIELDS);
        this.advantage = bitToMap(cond.getAdvantage(), MultipleOption.ADVANTAGES);
        this.busiType = bitToMap(cond.getBusiType(), MultipleOption.BUSITYPES);

        // 사용자 입장 에선 Exclude Category(필요없는 지원분야)이므로 비트플립 해서 넘김
        int invalidBitCnt = 32 - MultipleOption.CATEGORYS.length;
        int flipped_category = ((~(cond.getCategory()) << invalidBitCnt) >>> invalidBitCnt);
        this.excCategory = bitToMap(flipped_category, MultipleOption.CATEGORYS);
    }



    public static HashMap<String, Boolean> bitToMap(long binary, String[] arr) {
        HashMap<String, Boolean> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) { // 의미를 가진 모든 비트를 시프트연산 하면서 검사해서 t/f 설정
            map.put(arr[i], (binary&1) == 1);
            binary >>= 1;
        }
        return map;
    }
    public static HashMap<String, Boolean> bitToMap(int binary, String[] arr) {
        return bitToMap((long)binary, arr);
    }

}

