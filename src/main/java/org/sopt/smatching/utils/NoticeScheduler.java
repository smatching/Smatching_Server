package org.sopt.smatching.utils;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class NoticeScheduler {

    @Autowired
    private NoticeService noticeService;

    // 매일 0시 1분에 만료된 공고 비활성화
    @Scheduled(cron = "1 0 0 * * *") // 초, 분, 시, 일, 월, 요일 -> 매일 0시0분1초에 실행
    public void scanExpiredNoticesToInvalidation() {
        log.info("@@@@@ scanExpiredNoticesToInvalidation Method START!! @@@@@");
        try {
            List<Integer> list = noticeService.scanExpiredNoticesToInvalidation();
            log.info("@@@@@ scanExpiredNoticesToInvalidation Method DONE!! @@@@@\n- Invalidated noticeIdx List : " + list.toString() + "\n\n");
        } catch(Exception e) {
            log.error("@@@@@ scanExpiredNoticesToInvalidation Method fail!! @@@@@");
            log.error("Exception Detail (below)", e);
        }
    }
    
}
