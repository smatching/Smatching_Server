package org.sopt.smatching.utils;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.NoticeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class NoticeScheduler {

    private NoticeService noticeService;

    public NoticeScheduler(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

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


    // 매일 18시 30분에 스크랩 된 공고들을 스캔해서 dday가 3일 남은건 알람을 줌
    @Scheduled(cron = "0 30 18 * * *")
    public void scanD_3NoticesToNotify() {
        log.info("@@@@@ scanD_3NoticesToNotify Method START!! @@@@@");
        try {
            List<Integer> list = noticeService.scanD_3NoticesToNotify();
            log.info("@@@@@ scanD_3NoticesToNotify Method DONE!! @@@@@\n- D_3 noticeIdx List : " + list.toString() + "\n\n");

        } catch(Exception e) {
            log.error("@@@@@ scanD_3NoticesToNotify Method fail!! @@@@@");
            log.error("Exception Detail (below)", e);
        }
    }
    
}
