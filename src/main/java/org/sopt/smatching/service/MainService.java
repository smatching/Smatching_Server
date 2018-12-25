package org.sopt.smatching.service;

import org.sopt.smatching.dto.MainTabInfo;
import org.sopt.smatching.dto.NoticeSummary;
import org.sopt.smatching.mapper.NoticeMapper;
import org.sopt.smatching.mapper.UserMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtService jwtService;

    public DefaultRes getMainTabInfo(final String jwt) {

        MainTabInfo mainTabInfo = new MainTabInfo();

        // 전체공고 목록 최대 4개 획득 - 없으면 빈 리스트( [] )가 나감
        mainTabInfo.setAllNoticeSummary(noticeMapper.findAllNoticeSummary(4,0));


        // 토큰값 있으면 개인화정보도 채움
        if(jwt != null) {
            // 토큰 해독
            final JwtService.Token token = jwtService.decode(jwt);
            int userIdx = token.getUser_idx();

            // 비정상 토큰인 경우 - 닉네임만 "TokenErr"로 설정
            if(userIdx == -1) {
                mainTabInfo.setNickname("TokenErr");
            }

            // 정상 토큰인 경우 - 정보입력
            else {
                // 닉네임, 맞춤공고 전체개수, 맞춤공고 목록 (최대3개, 없으면 [] )획득
                // ### 쿼리문 하나로 처리하는게 좋을듯

                // 획득한 정보 입력
                mainTabInfo.setNickname("획득한이름");
                mainTabInfo.setFitNoticeCnt(123);
                mainTabInfo.setFitNoticeSummary(new ArrayList<NoticeSummary>());
            }
        }

        return DefaultRes.res(StatusCode.OK, ResponseMessage.MAIN_INFO_SUCCESS, mainTabInfo);
    }

}
