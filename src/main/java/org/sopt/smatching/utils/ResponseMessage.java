package org.sopt.smatching.utils;

public class ResponseMessage {

    public static final String READ_NOTICE_SUMMARY = "공고 목록 조회 성공";
    public static final String NOT_FOUND_NOTICE = "공고를 찾을 수 없습니다.";
    public static final String ALREADY_EXIST_EMAIL = "이미 존재하는 이메일 입니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String INTERNAL_SERVER_ERROR_IN_CONTROLLER = "컨트롤러 에러";

    public static final String DB_ERROR = "데이터베이스 에러";

    public static final String INVALID_TOKEN = "토큰 디코딩 실패";

    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";

    public static final String READ_COND_SUCCESS = "맞춤조건 조회 성공";

    public static final String MAIN_INFO_SUCCESS = "메인 페이지 정보 조회 성공";
}
