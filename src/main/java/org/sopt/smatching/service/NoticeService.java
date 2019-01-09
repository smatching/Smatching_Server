package org.sopt.smatching.service;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.cond.Cond;
import org.sopt.smatching.model.notice.Notice;
import org.sopt.smatching.model.notice.NoticeDetail;
import org.sopt.smatching.model.notice.NoticeSummary;
import org.sopt.smatching.model.user.UserAlert;
import org.sopt.smatching.mapper.CondMapper;
import org.sopt.smatching.mapper.NoticeMapper;
import org.sopt.smatching.mapper.ScrapMapper;
import org.sopt.smatching.mapper.UserMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.model.notice.NoticeInput;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.sopt.smatching.utils.auth.AuthAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class NoticeService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private CondMapper condMapper;
    @Autowired
    private ScrapMapper scrapMapper;
    @Autowired
    private UserMapper userMapper;



    // 전체 지원사업 개수 조회
    public DefaultRes getNoticeCnt() {
        final int noticeCnt = noticeMapper.countAllNotice();
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_ALL_NOTICE_CNT_SUCCESS, noticeCnt);
    }

    // 전체 지원사업 목록 조회 - 최신등록순으로 요청된 갯수만큼 리턴
    public DefaultRes getNoticeSummaryList(String jwt, int reqNum, int existNum) {
        List<NoticeSummary> noticeSummaryList;

        // 토큰값 없으면 조인 없는 쿼리문 사용, scrap은 모두 int 기본값인 0으로 설정됨
        if(jwt == null  || jwt == "")
            noticeSummaryList = noticeMapper.findAllNoticeSummary(reqNum, existNum);

        // 토큰값 있으면 스크랩 여부를 위해 조인 필요
        else {

            // 토큰 해독
            final JwtService.Token token = jwtService.decode(jwt);
            int userIdx = token.getUser_idx();

            // 비정상 토큰인 경우 403 리턴
            if(userIdx < 1)
                return AuthAspect.DEFAULT_RES_403;

            // scrap과 조인하는 쿼리문 사용
            noticeSummaryList = noticeMapper.findAllNoticeSummaryWithScrap(reqNum, existNum, userIdx);
        }

        // 한개도 검색되지 않았으면 204
        if (noticeSummaryList.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_NOTICE);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTICE_SUMMARY, noticeSummaryList);
    }


    /////// 위의 2개 메소드 Overload ////////
    // 맞춤조건에 매칭되는 지원사업 개수 조회
    public DefaultRes getNoticeCnt(int condIdx) {

        // condIdx로 맞춤조건 정보 획득
        final Cond cond = condMapper.findCondByCondIdx(condIdx);
        if(cond == null)
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_EXIST_COND);

        // 지원사업 개수 조회
        final int noticeCnt = noticeMapper.countFitNotice(cond);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_FIT_NOTICE_CNT_SUCCESS, noticeCnt);
    }

    // 맞춤 지원사업 목록 조회- 최신등록순으로 요청된 갯수만큼 리턴
    public DefaultRes getNoticeSummaryList(String jwt, int reqNum, int existNum, int condIdx) {

        // 토큰 없으면 401 리턴
        if(jwt == null || jwt == "")
            return AuthAspect.DEFAULT_RES_401;

        // 토큰 해독
        final JwtService.Token token = jwtService.decode(jwt);
        int userIdx = token.getUser_idx();

        // 비정상 토큰인 경우 403 리턴
        if(userIdx < 1)
            return AuthAspect.DEFAULT_RES_403;

        // cond 테이블에서 맞춤조건 정보 획득
        final Cond cond = condMapper.findCondByCondIdx(condIdx);
        if(cond == null)
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_EXIST_COND);

        // notice 테이블과 scrap과 조인하는 쿼리문 사용
        List<NoticeSummary> noticeSummaryList = noticeMapper.findFitNoticeSummaryWithScrap(reqNum, existNum, userIdx, cond);


        // 한개도 검색되지 않았으면 204
        if (noticeSummaryList.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_NOTICE);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTICE_SUMMARY, noticeSummaryList);
    }


    // 공고 상세 조회 + 조회수 1 증가
    public DefaultRes getDetail(int noticeIdx) {
        NoticeDetail noticeDetail = noticeMapper.findDetailByNoticeIdx(noticeIdx);
        if (noticeDetail == null)
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_FOUND_NOTICE);

        // 조회수 1 증가 - 실패해도 에러를 리턴하진 않고 그냥 로그만 남김
        try {
            noticeMapper.plusReadCnt(noticeIdx);
        } catch (Exception e) {
            log.error("\n- Exception Detail (below)", e);
        }

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTICE_DETAIL, noticeDetail);
    }

    ///////////////////////////////////////////////////////////////////////

    // 스크랩 여부 조회
    public DefaultRes getScrap(int userIdx, int noticeIdx) {
        // 현재 상태 count로 체크 (1 or 0)
        int scraped = scrapMapper.isScraped(userIdx, noticeIdx);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_SCRAP, scraped);
    }

    // 스크랩 여부 바꾸기
    @Transactional
    public DefaultRes changeScrap(int userIdx, int noticeIdx) {

        // 현재 상태 sql의 COUNT로 체크 (1 or 0)
        int scraped = scrapMapper.isScraped(userIdx, noticeIdx);

        if (scraped == 0) { // 스크랩 안돼있으면 row 추가
            int rowCnt = scrapMapper.insertScrap(userIdx, noticeIdx);
            if(rowCnt != 1) {
                log.error("--------------------------------------------");
                log.error("@@@@@ rowCnt is NOT 1 but " + rowCnt + " @@@@@");
                scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
            }

            return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_SCRAP, 1);
        }

        else if (scraped == 1) { // 스크랩 돼있으면 row 삭제
            int rowCnt = scrapMapper.deleteScrap(userIdx, noticeIdx);
            if(rowCnt != 1) {
                log.error("--------------------------------------------");
                log.error("@@@@@ rowCnt is NOT 1 but " + rowCnt + " @@@@@");
                scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
            }

            return DefaultRes.res(StatusCode.OK, ResponseMessage.DELETED_SCRAP, 0);
        }

        else { // PK(2개조합)으로 조회했기 때문에 COUNT의 결과가 무조건 0 아니면 1이어야함.. 여기로 오면 안됨
            log.error("--------------------------------------------");
            log.error("@@@@@ scraped is NOT 0 or 1 but " + scraped + " @@@@@");
            scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴

            return null; // 위에서 예외가 발생되기 때문에 여기로 넘어올일 없음
        }
    }


    // 유저가 스크랩한 지원사업 목록 조회
    public DefaultRes getScrapedNoticeList(int userIdx, int reqNum, int existNum) {
        List<NoticeSummary> noticeSummaryList = noticeMapper.findScrapedNoticeSummary(userIdx, reqNum, existNum);

        // 한개도 검색되지 않았으면 204
        if (noticeSummaryList.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_NOTICE);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTICE_SUMMARY, noticeSummaryList);
    }

    //////////////////////////////////////////////////////////////////////////////

    // 유저의 알람설정 여부 조회 (마이페이지 탭)
    public DefaultRes getAlert(int userIdx) {
        UserAlert userAlert = userMapper.findUserAlertByUserIdx(userIdx);

        HashMap<String, Boolean> map = new HashMap<>();
        map.put("talkAlert", userAlert.getTalkAlert() > 0);
        map.put("condAlert", userAlert.getCondAlert() > 0);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER_ALERT, map);
    }

    // 유저의 맞춤조건 알람설정 ON/OFF 토글 (마이페이지 탭의 설정화면)
    @Transactional
    public DefaultRes toggleCondAlert(int userIdx) {
        final Integer current = condMapper.findAlertByUserIdx(userIdx);

        if(current == null) { // 만든 맞춤조건이 없는 경우
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_COND, false); // 꺼진 상태 유지

        } else if(current > 0){ // 하나라도 켜져있는 경우
            final int rowCnt = condMapper.updateAlertByUserIdx(userIdx, 0); // 모두 끈다
            if(rowCnt < 1) {
                log.error("--------------------------------------------");
                log.error("@@@@@ rowCnt is smaller than 1, rowCnt : " + rowCnt + " @@@@@");
                scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
            }

            return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_USER_COND_ALERT, false);
        }
        else { // 이미 모두 꺼져있는 경우
            final int rowCnt = condMapper.updateAlertByUserIdx(userIdx, 1); // 모두 킨다
            if(rowCnt < 1) {
                log.error("--------------------------------------------");
                log.error("@@@@@ rowCnt is smaller than 1, rowCnt : " + rowCnt + " @@@@@");
                scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
            }

            return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_USER_COND_ALERT, true);
        }

    }


    // 유저의 창업토크 알람설정 ON/OFF 토글 (마이페이지 탭의 설정화면)
    @Transactional
    public DefaultRes toggleTalkAlert(int userIdx) {
        final int current = userMapper.findTalkAlertByUserIdx(userIdx);

        try {
            if(current == 0) { // 꺼져 있으면
                final int rowCnt = userMapper.updateTalkAlertByUserIdx(userIdx, 1); // 켜는걸로 변경
                if(rowCnt != 1) {
                    log.error("--------------------------------------------");
                    log.error("@@@@@ rowCnt is NOT 1 but " + rowCnt + " @@@@@");
                    scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
                }

                return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_USER_TALK_ALERT, true);
            }
            else { // 켜져있으면
                final int rowCnt = userMapper.updateTalkAlertByUserIdx(userIdx, 0); // 끄는걸로 변경
                if(rowCnt != 1) {
                    log.error("--------------------------------------------");
                    log.error("@@@@@ rowCnt is NOT 1 but " + rowCnt + " @@@@@");
                    scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
                }

                return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_USER_TALK_ALERT, false);
            }

        } catch(Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error("\n- Exception Detail (below)", e);
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    //////////////////////////////// 관리자용 서비스 메소드들 //////////////////////////////////////////////

    // 새 지원사업 저장
    @Transactional
    public DefaultRes addNotice(NoticeInput noticeInput) {
        Notice notice = new Notice(noticeInput);

        // notice 테이블에 저장하고 AI로 생성된 noticeIdx로 detail 테이블에도 저장
        int a = noticeMapper.save(notice);
        int b = scrapMapper.insertScrap(-1, -1);
        int c = noticeMapper.saveDetail(notice);
        if(a!=0 || b!=0 || c!=0) {
            log.error("--------------------------------------------");
            log.error("@@@@@ rowCnts are NOT 1,1,1 but " + a + ',' + b + ',' + c + " @@@@@");
            scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
        }

        if(noticeInput.isNotfit()) { // 기타공고면 update문으로 notift 1로 만들기
            int rowCnt = noticeMapper.makeNotFit(notice.getNoticeIdx());
            if(rowCnt != 1) {
                log.error("--------------------------------------------");
                log.error("@@@@@ rowCnt is NOT 1 but " + rowCnt + " @@@@@");
                scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
            }
        }

        return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_NOTICE);
    }

    // 지원사업 공고 비활성화
    @Transactional
    public DefaultRes invalidateNotice(int noticeIdx) {
        int rowCnt = noticeMapper.invalidate(noticeIdx);
        if(rowCnt != 1) {
            log.error("--------------------------------------------");
            log.error("@@@@@ rowCnt is NOT 1 but " + rowCnt + " @@@@@");
            scrapMapper.insertScrap(-1, -1); // 강제로 예외 발생시킴
        }

        return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.INVALIDATED_NOTICE);
    }


    // 전체 지원사업 공고 모든 정보 불러오기
    public List<Notice> getNoticeListForAdmin() {
        return noticeMapper.findNoticeEverything();
    }

}
