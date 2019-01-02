package org.sopt.smatching.utils;

public class ResponseMessage {

    public static final String READ_NOTICE_SUMMARY = "공고 목록 조회 성공";
    public static final String NOT_FOUND_NOTICE = "공고를 찾을 수 없습니다.";
    public static final String ALREADY_EXIST_EMAIL = "이미 존재하는 이메일 입니다.";
    public static final String READ_USER = "회원정보 조회 성공";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATED_USER = "회원 정보 수정 성공";
    public static final String DELETED_USER = "회원 탈퇴 성공";
    public static final String WRONG_PASSWORD = "패스워드 틀림";

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String INTERNAL_SERVER_ERROR_IN_CONTROLLER = "컨트롤러 에러";

    public static final String DB_ERROR = "데이터베이스 에러";
    public static final String DB_UPDATE_IS_ZERO = "데이터베이스 변경 Row가 없음";
    public static final String DB_UPDATE_IS_NOT_ONE = "데이터베이스 변경 Row가 1개가 아님";


    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";

    public static final String READ_USERCOND_SUCCESS = "유저의 맞춤조건 현황 조회 성공";

    public static final String NOT_EXIST_COND = "존재하지 않는 맞춤조건 인덱스 입니다.";

    public static final String READ_FIT_NOTICE_CNT_SUCCESS = "맞춤 지원공고 개수 조회 성공";


    public static final String READ_COND = "맞춤조건 조회 성공";
    public static final String NOT_FOUND_COND = "설정한 맞춤조건이 없습니다.";
    public static final String CREATED_COND = "맞춤조건 추가 성공";
    public static final String UPDATED_COND = "맞춤조건 변경 성공";
    public static final String DELETED_COND = "맞춤조건 삭제 성공";
    public static final String UPDATED_COND_ALERT = "맞춤조건 알람설정 변경 성공";

    public static final String READ_SCRAP = "스크랩 여부 조회 성공";
    public static final String CREATED_SCRAP = "스크랩 설정 성공";
    public static final String DELETED_SCRAP = "스크랩 해제 성공";

    public static final String READ_USER_INFO = "마이페이지 메인 정보 조회 성공";
    public static final String READ_USER_ALERT = "유저의 알람설정 여부 조회 성공";
    public static final String UPDATED_USER_COND_ALERT = "유저의 맞춤공고 알람설정 변경 성공";
    public static final String UPDATED_USER_TALK_ALERT = "유저의 창업토크 알람설정 변경 성공";
}
