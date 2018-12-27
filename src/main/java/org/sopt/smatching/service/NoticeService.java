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
    @Autowired
    private JwtService jwtService;


    // 공고 목록 전체조회 - 최신등록순으로 요청된 갯수만큼 리턴
    public DefaultRes getNoticeSummaryList(String jwt, int reqNum, int existNum) {
        List<NoticeSummary> noticeSummaryList;

        // 토큰값 없으면 조인 없는 쿼리문 사용, scrap은 모두 int 기본값인 0으로 설정됨
        if(jwt == null)
            noticeSummaryList = noticeMapper.findAllNoticeSummary(reqNum, existNum);

        // 토큰값 있으면
        else {

            // 토큰 해독
            final JwtService.Token token = jwtService.decode(jwt);
            int userIdx = token.getUser_idx();

            // 비정상 토큰인 경우 에러 출력
            if (userIdx == -1) {
                return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INVALID_TOKEN);
            }
            // scrap과 조인하는 쿼리문 사용
            noticeSummaryList = noticeMapper.findAllNoticeSummaryWithScrap(reqNum, existNum, userIdx);
        }

        // 한개도 검색되지 않았으면 204
        if (noticeSummaryList.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_NOTICE);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTICE_SUMMARY, noticeSummaryList);
    }


    // 공고 목록 맞춤조회 - 최신등록순으로 요청된 갯수만큼 리턴
    public DefaultRes getNoticeSummaryList(String jwt, int reqNum, int existNum, int condIdx) {
        List<NoticeSummary> noticeSummaryList;

        // 토큰값 없으면 조인 없는 쿼리문 사용, scrap은 모두 int 기본값인 0으로 설정됨
        if(jwt == null)
            noticeSummaryList = noticeMapper.findFitNoticeSummary(reqNum, existNum, condIdx);

        // 토큰값 있으면
        else {

            // 토큰 해독
            final JwtService.Token token = jwtService.decode(jwt);
            int userIdx = token.getUser_idx();

            // 비정상 토큰인 경우 에러 출력
            if (userIdx == -1) {
                return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INVALID_TOKEN);
            }
            // scrap과 조인하는 쿼리문 사용
            noticeSummaryList = noticeMapper.findFitNoticeSummaryWithScrap(reqNum, existNum, condIdx, userIdx);
        }





        noticeMapper.findFitNoticeSummary(reqNum, existNum, condIdx);






        // 한개도 검색되지 않았으면 204
        if (noticeSummaryList.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_NOTICE);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTICE_SUMMARY, noticeSummaryList);
    }



    // 공고 상세 조회
    public DefaultRes getNotice(int noticeIdx) {

        return null;
    }

}
