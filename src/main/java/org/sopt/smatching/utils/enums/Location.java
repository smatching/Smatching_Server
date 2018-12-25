package org.sopt.smatching.utils.enums;

// ENUM('ALL', 'SEOUL', 'BUSAN', 'DAEGU', 'INCHEON', 'GWANGJU', 'DAEJEON', 'ULSAN', 'SEJONG', 'GANGWON', 'KYUNGGI', 'KYUNGNAM', 'KYUNGBUK', 'JEONNAM', 'JEONBUK', 'CHUNGNAM', 'CHUNGBUK', 'JEJU', 'ABORAD')

public enum Location {
    ALL(1), // 전국
    SEOUL(2), // 서울
    BUSAN(3), // 부산
    DAEGU(4), // 대구
    INCHEON(5), // 인천
    GWANGJU(6), // 광주
    DAEJEON(7), // 대전
    ULSAN(8), // 울산
    SEJONG(9), // 세종
    GANGWON(10), // 강원
    KYUNGGI(11), // 경기
    KYUNGNAM(12), // 경남
    KYUNGBUK(13), // 경북
    JEONNAM(14), // 전남
    JEONBUK(15), // 전북
    CHUNGNAM(16), // 충남
    CHUNGBUK(17), // 충북
    JEJU(18), // 제주
    ABORAD(19); // 국외


    private final int value;

    Location(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Location valueOf(int value) {
        switch(value) {
            case 1: return Location.ALL;
            case 2: return Location.SEOUL;
            case 3: return Location.BUSAN; // 부산
            case 4: return Location.DAEGU; // 대구
            case 5: return Location.INCHEON; // 인천
            case 6: return Location.GWANGJU; // 광주
            case 7: return Location.DAEJEON; // 대전
            case 8: return Location.ULSAN; // 울산
            case 9: return Location.SEJONG; // 세종
            case 10: return Location.GANGWON; // 강원
            case 11: return Location.KYUNGGI; // 경기
            case 12: return Location.KYUNGNAM; // 경남
            case 13: return Location.KYUNGBUK; // 경북
            case 14: return Location.JEONNAM; // 전남
            case 15: return Location.JEONBUK; // 전북
            case 16: return Location.CHUNGNAM; // 충남
            case 17: return Location.CHUNGBUK; // 충북
            case 18: return Location.JEJU; // 제주
            case 19: return Location.ABORAD; // 국외

            default: throw new AssertionError("Unknown Location: " + value);
        }
    }
}