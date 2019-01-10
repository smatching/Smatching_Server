package org.sopt.smatching.model.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NotificationOutput {

    private int noticeIdx;
    private String alertType;
    private String message;
    private boolean checked;
    private String outputTime;



    public void writeOutputTime() {
        if(this.outputTime == null)
            this.outputTime = "알 수 없음";

        else {
            try {
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                Date timestamp = dt.parse(this.outputTime);

                long diff = new Date().getTime() - (timestamp.getTime());
                int diffSec = (int) (diff / 1000L);
                int diffMin = diffSec / 60;
                int diffHour = diffMin / 60;

                String output;
                if(diffHour < 24) {
                    output = diffHour + "시간 전";
                    if(diffMin < 60) {
                        output = diffMin + "분 전";
                        if(diffSec < 60)
                            output = diffSec + "초 전";
                    }
                }
                else
                    output = new SimpleDateFormat("MM/dd HH:mm").format(timestamp);

                this.outputTime = output;

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
