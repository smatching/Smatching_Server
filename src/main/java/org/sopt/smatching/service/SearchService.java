package org.sopt.smatching.service;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.mapper.SearchMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.model.notice.NoticeSummary;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.sopt.smatching.utils.auth.AuthAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Slf4j
@Service
public class SearchService {

    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private JwtService jwtService;


    // 전체 지원사업 검색 개수 조회 기능
    public DefaultRes countFromEverywhere(String query) {
        final int cnt = searchMapper.countNoticeFromEverywhere(query);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.SEARCH_COUNT_SUCCESS, cnt);
    }

    // 전체 지원사업 검색 기능
    public DefaultRes fromEverywhere(String jwt, String query, int reqNum, int existNum) {

        List<NoticeSummary> notices;

        // 토큰 있으면 검색어 저장하고 스크랩 여부와 조인하는 쿼리 사용
        if(!(jwt == null || jwt == "")) {
            // 토큰 해독
            final JwtService.Token token = jwtService.decode(jwt);
            int userIdx = token.getUser_idx();

            // 비정상 토큰인 경우 403 리턴
            if(userIdx < 1)
                return AuthAspect.DEFAULT_RES_403;

            // 최근 검색어로 저장 - 실패해도 에러를 리턴하진 않고 그냥 로그만 남김
            try {
                searchMapper.saveQueryLog(userIdx, query);
            } catch(Exception e) {
                log.error("\n- Exception Detail (below)", e);
            }

            // 검색 쿼리 (스크랩 여부까지)
            notices = searchMapper.noticeFromEverywhereIncScrap(userIdx, query, reqNum, existNum);
        }
        // 토큰 없으면 스크랩 여부 없이 검색하는 쿼리만 사용 - scarp 은 무조건 0 으로 채워짐
        else {
            notices = searchMapper.noticeFromEverywhere(query, reqNum, existNum);
        }


        if(notices.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.SEARCH_NO_RESULT);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.SEARCH_SUCCESS, notices);
    }


    // 유저의 최근 검색어 조회
    public DefaultRes getUserQueryLogs(int userIdx) {
        List<String> queryList = searchMapper.findQueryLogsByUserIdx(userIdx);
        if(queryList.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_QUERY_LOG);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_QUERY_LOG, queryList);
    }


    // 유저의 최근 검색어 삭제
    @Transactional
    public DefaultRes deleteUserQueryLog(int userIdx, int order) {
        final int searchLogIdx = searchMapper.findSearchLogIdx(userIdx, order);

        try {
            searchMapper.deleteBySearchLogIdx(searchLogIdx);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.DELETED_QUERY_LOG);

        } catch(Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error("\n- Exception Detail (below)", e);
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }


    // 맞춤지원 스크랩에서 검색
    public DefaultRes fromScrapNotice(int userIdx, String query, int reqNum, int existNum) {
        List<NoticeSummary> notices = searchMapper.fromScrapNotice(userIdx, query, reqNum, existNum);
        if(notices.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.SEARCH_NO_RESULT);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.SEARCH_SUCCESS, notices);
    }


    // 맞춤지원 스크랩에서 검색 결과 개수만 조회
    public DefaultRes countFromScrapNotice(int userIdx, String query) {
        final int cnt = searchMapper.countFromScrapNotice(userIdx, query);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.SEARCH_COUNT_SUCCESS, cnt);
    }
}
