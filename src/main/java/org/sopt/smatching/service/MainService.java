package org.sopt.smatching.service;

import org.sopt.smatching.dto.MainTabInfo;
import org.sopt.smatching.dto.User;
import org.sopt.smatching.mapper.NoticeMapper;
import org.sopt.smatching.mapper.UserMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // 전체 공고 정보 4개 획득
        mainTabInfo.setAllNoticeSummary(noticeMapper.findAllNoticeSummary(4,0));

        // 토큰값 있으면 해독해서 이름, 맞춤지원 공고 개수, 맞춤지원 공고 목록 3개 리턴
        if(jwt != null) {
            final JwtService.Token token = jwtService.decode(jwt); //토큰 해독

            System.out.println("userIdx is : " + token.getUser_idx());

            mainTabInfo.setNickname("테스트");
            mainTabInfo.setFitNoticeCnt(123);
            mainTabInfo.setFitNoticeSummary(noticeMapper.findAllNoticeSummary(3,0));
        }

        return DefaultRes.res(StatusCode.OK, ResponseMessage.MAIN_INFO_SUCCESS, mainTabInfo);
    }

}
