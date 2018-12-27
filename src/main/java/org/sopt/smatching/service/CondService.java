package org.sopt.smatching.service;

import org.sopt.smatching.dto.Cond;
import org.sopt.smatching.mapper.CondMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondService {

    @Autowired
    private CondMapper condMapper;
    @Autowired
    private JwtService jwtService;

    // 토큰에서 userIdx를 추출해서 맞춤조건 조회 - 0개 or 1개 or 2개가 채워진 리스트 반환
    public DefaultRes getCondList(final String jwt) {

        // 토큰 해독
        final JwtService.Token token = jwtService.decode(jwt);
        int userIdx = token.getUser_idx();

        // 비정상 토큰인 경우 에러 리턴
        if(userIdx == -1) {
            return new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INVALID_TOKEN);
        }

        List<Cond> condList = condMapper.findByUserIdx(userIdx);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_COND_SUCCESS, condList);
    }

}
