package org.sopt.smatching.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sopt.smatching.utils.Age;
import org.sopt.smatching.utils.MutilpleOption;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class CondDetail {

    private int condIdx;
    private String condName;
    private boolean alert;
    private HashMap<String, Boolean> location;
    private Age age; // 단일선택 옵션이라 enum 타입으로 정의
    private HashMap<String, Boolean> period;
    private HashMap<String, Boolean> category;
    private HashMap<String, Boolean> field;
    private HashMap<String, Boolean> advantage;
    private HashMap<String, Boolean> busiType;


    // Cond -> CondDetail 생성자
    public CondDetail(Cond cond) {

        this.condIdx = cond.getCondIdx();
        this.condName = cond.getCondName();
        this.alert = cond.isAlert();

        this.location = bitToMap(cond.getLocation(), MutilpleOption.LOCATIONS);
        this.age = cond.getAge(); // ### enum 이용과정 코드에 문제없나 확인 필요
        this.period = bitToMap(cond.getPeriod(), MutilpleOption.PERIODS);
        this.category = bitToMap(cond.getCategory(), MutilpleOption.CATEGORYS);
        this.field = bitToMap(cond.getField(), MutilpleOption.FIELDS);
        this.advantage = bitToMap(cond.getAdvantage(), MutilpleOption.ADVANTAGES);
        this.busiType = bitToMap(cond.getBusiType(), MutilpleOption.BUSITYPES);
    }



    public static HashMap<String, Boolean> bitToMap(long binary, String[] arr) {
        HashMap<String, Boolean> map = new HashMap<>();

        if((binary&1) == 1) { // 최하위 비트가 1이면, 모든 객관식 보기를 전부 true로 설정
            for (String key : arr)
                map.put(key, true);
        }
        else { // // 최하위 비트가 1이 아니면, 그 다음비트부터 의미를 가진 모든 비트를 시프트연산 하면서 검사해서 t/f 설정
            for (int i = 0; i < arr.length; i++) {
                binary >>= 1;
                map.put(arr[i], (binary&1) == 1);
            }
        }
        return map;
    }
    public static HashMap<String, Boolean> bitToMap(int binary, String[] arr) {
        return bitToMap((long)binary, arr);
    }

}

