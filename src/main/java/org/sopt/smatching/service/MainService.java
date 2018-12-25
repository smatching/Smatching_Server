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

        // 전체공고 목록 최대 4개 획득 - 없으면 빈 리스트( [] )가 나감
        mainTabInfo.setAllNoticeSummary(noticeMapper.findAllNoticeSummary(4,0));


        // 토큰값 있으면 개인화정보도 채움
        if(jwt != null) {
            //토큰 해독
            final JwtService.Token token = jwtService.decode(jwt);

            // 닉네임 획득
            mainTabInfo.setNickname(userMapper.findNicknameByUserIdx(token.getUser_idx()));

            // 맞춤공고 전체개수만 획득
            mainTabInfo.setFitNoticeCnt(1234);

            // 맞춤공고 목록 최대 3개 획득 - 없으면 빈 리스트( [] )가 나감?!
            mainTabInfo.setFitNoticeSummary(null);
        }

        return DefaultRes.res(StatusCode.OK, ResponseMessage.MAIN_INFO_SUCCESS, mainTabInfo);
    }

}
