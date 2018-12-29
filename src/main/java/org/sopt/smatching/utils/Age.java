package org.sopt.smatching.utils;

// ENUM('ALL', 'TWENTY_LESS', 'TWENTY_FORTY', 'FORTY_MORE')

public enum Age { // 나이
    ALL(1), // 무관
    TWENTY_LESS(2), // 만 20세 미만
    TWENTY_FOURTY(3), // 만 20세 이상 ~ 만 40세 미만
    FORTY_MORE(4); //만 40세 이상

    private final int value;

    Age(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Age valueOf(int value) {
        switch(value) {
            case 1: return Age.ALL;
            case 2: return Age.TWENTY_LESS;
            case 3: return Age.TWENTY_FOURTY;
            case 4: return Age.FORTY_MORE;

            default: throw new AssertionError("Unknown Age: " + value);
        }
    }
}