package org.sopt.smatching.service;

import org.sopt.smatching.dto.Cond;
import org.sopt.smatching.dto.NoticeSummary;
import org.sopt.smatching.mapper.CondMapper;
import org.sopt.smatching.mapper.NoticeMapper;
import org.sopt.smatching.mapper.UserMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private CondMapper condMapper;
    @Autowired
    private UserMapper userMapper;

    // 전체 지원사업 개수 조회
    public DefaultRes getNoticeCnt() {
        final int noticeCnt = noticeMapper.countAllNotice();
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_FIT_NOTICE_CNT_SUCCESS, noticeCnt);
    }

    // 전체 지원사업 목록 조회 - 최신등록순으로 요청된 갯수만큼 리턴
    public DefaultRes getNoticeSummaryList(String jwt, int reqNum, int existNum) {
        List<NoticeSummary> noticeSummaryList;

        // 토큰값 없으면 조인 없는 쿼리문 사용, scrap은 모두 int 기본값인 0으로 설정됨
        if(jwt == null)
            noticeSummaryList = noticeMapper.findAllNoticeSummary(reqNum, existNum);

        // 토큰값 있으면 스크랩 여부를 위해 조인 필요
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


    /////// 위의 2개 메소드 Overload ////////
    // 맞춤조건에 매칭되는 지원사업 개수 조회 - Mappper만 수정하면됨
    public DefaultRes getNoticeCnt(int condIdx) {

        // 맞춤조건 정보 획득
        final Cond cond = condMapper.findByCondIdx(condIdx);
        if(cond == null)
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_EXIST_COND);

        // 지원사업 개수 조회
        final int noticeCnt = noticeMapper.countFitNotice(cond);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_FIT_NOTICE_CNT_SUCCESS, noticeCnt);
    }

    // 맞춤 지원사업 목록 조회- 최신등록순으로 요청된 갯수만큼 리턴 - Mappper만 수정하면됨
    public DefaultRes getNoticeSummaryList(String jwt, int reqNum, int existNum, int condIdx) {

        // 토큰이 없으면 403 리턴
        if(jwt == null)
            return new DefaultRes(StatusCode.FORBIDDEN, ResponseMessage.NOT_EXIST_TOKEN);

        // 토큰 해독
        final JwtService.Token token = jwtService.decode(jwt);
        int userIdx = token.getUser_idx();

        // 비정상 토큰인 경우 에러 출력
        if (userIdx == -1) {
            return DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INVALID_TOKEN);
        }

        // scrap과 조인하는 쿼리문 사용
        final Cond cond = condMapper.findByCondIdx(condIdx);
        List<NoticeSummary> noticeSummaryList = noticeMapper.findFitNoticeSummaryWithScrap(reqNum, existNum, userIdx, cond);

        // 한개도 검색되지 않았으면 204
        if (noticeSummaryList.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_NOTICE);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTICE_SUMMARY, noticeSummaryList);
    }



    ///////////////////////////////////////////////////////////////////////



    // 공고 상세 조회 - 새로 작성해야함
    public DefaultRes getNotice(int noticeIdx) {

        return null;
    }

}
