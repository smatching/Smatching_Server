package org.sopt.smatching.model.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Notification {

    private int notificationIdx;
    private int userIdx;
    private String alertType;
    private String message;

    public Notification(int userIdx, String alertType, String message) {
        this.userIdx = userIdx;
        this.alertType = alertType;
        this.message = message;
    }

}
