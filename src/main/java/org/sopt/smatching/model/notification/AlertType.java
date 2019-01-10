package org.sopt.smatching.model.notification;

public enum AlertType {

    NewNotice(1),
    ThreeDaysLeft(2);

    private final int value;

    AlertType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AlertType valueOf(int value) {
        switch(value) {
            case 1: return AlertType.NewNotice;
            case 2: return AlertType.ThreeDaysLeft;
            default: throw new AssertionError("Unknown AlertType : " + value);
        }
    }
}
