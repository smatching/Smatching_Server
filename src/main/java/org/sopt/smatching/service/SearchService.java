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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class SearchService {

    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private JwtService jwtService;

    // 전체 검색 (상단바)
    public DefaultRes fromEverywhere(String jwt, String query) {

        // 토큰 있으면 검색어 저장
        if(!(jwt == null || jwt == "")) {
            // 토큰 해독
            final JwtService.Token token = jwtService.decode(jwt);
            int userIdx = token.getUser_idx();

            // 비정상 토큰인 경우 403 리턴
            if(userIdx < 1)
                return AuthAspect.DEFAULT_RES_403;

            // 저장 - 실패해도 에러를 리턴하진 않고 그냥 로그만 남김
            try {
                searchMapper.saveQueryLog(userIdx, query);
            } catch(Exception e) {
                log.error(e.getMessage());
            }
        }


        // map 생성해서 각 분야별로 검색하고 결과를 넣음
        HashMap<String, List> map = new HashMap<String, List>();

        List<NoticeSummary> notices = searchMapper.noticeFromEverywhere(query);
        map.put("notices", notices.isEmpty() ? null : notices);

        map.put("talks", null);
        map.put("plans", null);


        // 검색한 분야들중에 결과가 하나라도 있는게 존재하면 정상 리턴, 하지만 없는 분야의 value는 null
        for(String key : map.keySet()) {
            if(map.get(key) != null)
                return DefaultRes.res(StatusCode.OK, ResponseMessage.SEARCH_SUCCESS, map);
        }

        // 모든 키에 결과가 없으면 204 리턴
        return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.SEARCH_NO_RESULT);
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
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

}
