package org.sopt.smatching.utils;

public class MultipleOption {

    /** 복수선택 가능한 객관식 옵션들 정의
     *
     *  비트의미 : 최하위 비트가 1이면 모두 True이고,
     *                         0이면 2^1 비트부터 배열의 0번째 원소의 T/F로 매핑
     *
     **/

    public static final String[] LOCATIONS = { "domesticAll",      "seoul",    "busan",    "daegu",   "incheon",
                                               "gwangju",  "daejeon",  "ulsan",    "sejong",  "gangwon",
                                               "kyunggi",  "kyungnam", "kyungbuk", "jeonnam", "jeonbuk",
                                               "chungnam", "chungbuk", "jeju",     "aborad" };

    public static final String[] AGES = { "twenty_less", "twenty_forty", "forty_more" };

    public static final String[] PERIODS = { "zero_one", "one_two",   "two_three",  "three_four", "four_five",
                                             "five_six", "six_seven", "seven_more", "yet" };

    public static final String[] CATEGORYS = { "edu",  "know", "place", "local", "global",
                                               "make", "gov",  "loan" };

    public static final String[] FIELDS = { "a", "b", "c", "d", "e",
                                            "f", "g", "h", "i", "j",
                                            "k", "l", "m", "n", "o",
                                            "p", "q", "r", "s", "t",
                                            "u", "v" };

    public static final String[] ADVANTAGES = { "retry",  "woman", "disabled", "social", "sole",
                                                "fourth", "univ",  "togather" };

    public static final String[] BUSITYPES = { "pre",   "midsmall", "midbig", "big", "sole",
                                               "small", "tradi" };

}
