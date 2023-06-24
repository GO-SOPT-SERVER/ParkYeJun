package sopt.org.FourthSeminar.exception.model;

import lombok.Getter;
import sopt.org.FourthSeminar.exception.Error;

/**
 * 서비스만의 예외처리를 커스텀하는 클래스 -> 일단 프로젝트명 + 'Exception'이라고 명칭
 * 어떤 에러인지 조금 더 상세하게 설명해주기 위해 String message를 추가해줌
 * -> 무슨 이유로 Not Found, Conflict 등의 Exception이 발생하는지 자세하게 설명을 적어줄 수 있다.
 */
@Getter
public class SoptException extends RuntimeException {

    private final Error error;

    public SoptException(Error error, String message) {
        super(message);
        this.error = error;
    }

    public SoptException(Error error) {
        super(error.getMessage());
        this.error = error;
    }

    public int getHttpStatus() {
        return error.getHttpStatusCode();
    }
}
