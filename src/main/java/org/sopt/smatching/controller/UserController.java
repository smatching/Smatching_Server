package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.model.LoginReq;
import org.sopt.smatching.model.SignUpReq;
import org.sopt.smatching.model.UserModifyReq;
import org.sopt.smatching.service.CondService;
import org.sopt.smatching.service.NoticeService;
import org.sopt.smatching.service.UserService;
import org.sopt.smatching.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CondService condService;
    @Autowired
    private NoticeService noticeService;

    // 회원 가입
    @PostMapping("")
    public ResponseEntity signUp(@RequestBody SignUpReq signUpReq) {
        return new ResponseEntity<>(userService.signUp(signUpReq), HttpStatus.OK);
    }

    // 로그인 수행, data 키의 값 : 로그인 성공시 토큰값 / 실패시 null
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody final LoginReq loginReq) {
        return new ResponseEntity<>(userService.login(loginReq), HttpStatus.OK);
    }


    // 유저의 맞춤조건 현황 조회
    @Auth
    @GetMapping("/cond")
    public ResponseEntity getCondInfoByToken(@RequestHeader(required = false, defaultValue = "0") int idx_variable) {
        return new ResponseEntity<>(condService.getCondInfoByToken(idx_variable), HttpStatus.OK);
    }

    // 마이페이지 메인 정보 조회
    @Auth
    @GetMapping("/myinfo")
    public ResponseEntity getMyPageMainInfo(@RequestHeader(required = false, defaultValue = "0") int idx_variable) {
        return new ResponseEntity<>(userService.getMyPageMainInfo(idx_variable), HttpStatus.OK);
    }

    // 유저가 스크랩한 지원사업 목록 조회
    @Auth
    @GetMapping("/noticelist")
    public ResponseEntity getScrapedNoticeList(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                               @RequestParam(value = "request_num") final int reqNum,
                                               @RequestParam(value = "exist_num") final int existNum) {
        return new ResponseEntity<>(noticeService.getScrapedNoticeList(idx_variable, reqNum, existNum), HttpStatus.OK);
    }

    // 유저의 알람설정 여부 조회 (마이페이지 탭의 설정화면)
    @Auth
    @GetMapping("/alert")
    public ResponseEntity getAlert(@RequestHeader(required = false, defaultValue = "0") int idx_variable) {
        return new ResponseEntity<>(noticeService.getAlert(idx_variable), HttpStatus.OK);
    }

    // 유저의 맞춤조건 알람설정 ON/OFF 토글 (마이페이지 탭의 설정화면)
    @Auth
    @PutMapping("/alert/cond")
    public ResponseEntity toggleCondAlert(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                          @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = noticeService.toggleCondAlert(idx_variable);
            defaultRes.setData(
                    new HashMap<String, Boolean>() {{
                        put("result", (Boolean)defaultRes.getData());
                    }}
            );
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        /**
         * iOS 요청에 따른 임시 방편
         */


        return new ResponseEntity<>(noticeService.toggleCondAlert(idx_variable), HttpStatus.OK);
    }

    // 유저의 창업토크 알람설정 ON/OFF 토글 (마이페이지 탭의 설정화면)
    @Auth
    @PutMapping("/alert/talk")
    public ResponseEntity toggleTalkAlert(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                          @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = noticeService.toggleTalkAlert(idx_variable);
            defaultRes.setData(
                    new HashMap<String, Boolean>() {{
                        put("result", (Boolean)defaultRes.getData());
                    }}
            );
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        /**
         * iOS 요청에 따른 임시 방편
         */


        return new ResponseEntity<>(noticeService.toggleTalkAlert(idx_variable), HttpStatus.OK);
    }


    // 회원정보 조회 기능 - 회원정보변경 창 띄울때
    @Auth
    @GetMapping("/edit")
    public ResponseEntity getUserInfo(@RequestHeader(required = false, defaultValue = "0") int idx_variable) {
        return new ResponseEntity<>(userService.getUserInfo(idx_variable), HttpStatus.OK);
    }

    // 회원정보변경
    @Auth
    @PutMapping("/edit")
    public ResponseEntity modifyUserInfo(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                         @RequestBody UserModifyReq userModifyReq) {
        return new ResponseEntity<>(userService.modifyUserInfo(idx_variable, userModifyReq), HttpStatus.OK);
    }


    // 프로필사진 변경
    @Auth
    @PutMapping("/picture")
    public ResponseEntity modifyProfilePicture(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                               @RequestPart(value="picture") final MultipartFile picture) {
        return new ResponseEntity<>(userService.modifyProfilePicture(idx_variable, picture), HttpStatus.OK);
    }

    // 프로필사진 삭제
    @Auth
    @DeleteMapping("/picture")
    public ResponseEntity modifyProfilePicture(@RequestHeader(required = false, defaultValue = "0") int idx_variable) {
        return new ResponseEntity<>(userService.deleteProfilePicture(idx_variable), HttpStatus.OK);
    }


    // 회원탈퇴
    @Auth
    @DeleteMapping("")
    public ResponseEntity modifyUserInfo(@RequestHeader(required = false, defaultValue = "0") int idx_variable) {
        return new ResponseEntity<>(userService.deleteUser(idx_variable), HttpStatus.OK);
    }
}
