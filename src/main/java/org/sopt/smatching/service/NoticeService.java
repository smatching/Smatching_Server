package org.sopt.smatching.service;

import org.sopt.smatching.dto.NoticeSummary;
import org.sopt.smatching.mapper.NoticeMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    // 공고 목록 전체조회 - 최신등록순으로 요청된 갯수만큼 리턴
    public DefaultRes getNoticeSummaryList(int reqNum, int existNum) {

        final List<NoticeSummary> noticeSummaryList = noticeMapper.findAllNoticeSummary(reqNum, existNum);

        // 한개도 검색되지 않았으면 404
        if (noticeSummaryList.isEmpty())
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_NOTICE);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTICE_SUMMARY, noticeSummaryList);
    }


    // 공고 목록 맞춤조회 - 최신등록순으로 요청된 갯수만큼 리턴
    public DefaultRes getNoticeSummaryList(int reqNum, int existNum, int condiIdx) {

        return null;
    }



    // 공고 상세 조회
    public DefaultRes getNotice(int noticeIdx) {

        return null;
    }

}
