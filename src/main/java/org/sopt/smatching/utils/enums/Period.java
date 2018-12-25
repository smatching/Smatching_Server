package org.sopt.smatching.utils.enums;

// ENUM('YET', 'ZERO_ONE', 'ONE_TWO', 'TWO_THREE', 'THREE_FOUR', 'FOUR_FIVE', 'FIVE_SIX', 'SIX_SEVEN', 'SEVEN_MORE')

public enum Period {

    YET(1), // 예비창업자
    ZERO_ONE(2), // 0년이상 ~ 1년미만
    ONE_TWO(3), // 1년이상 ~ 2년미만
    TWO_THREE(4), // 2년이상 ~ 3년미만
    THREE_FOUR(5), // 3년이상 ~ 4년미만
    FOUR_FIVE(6), // 4년이상 ~ 5년미만
    FIVE_SIX(7), // 5년이상 ~ 6년미만
    SIX_SEVEN(8), // 6년이상 ~ 7년미만
    SEVEN_MORE(9); // 7년이상

    private final int value;

    Period(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Period valueOf(int value) {
        switch(value) {
            case 1: return Period.YET;

            default: throw new AssertionError("Unknown Age: " + value);
        }
    }
}
