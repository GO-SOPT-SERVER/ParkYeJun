package sopt.org.FourthSeminar.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sopt.org.FourthSeminar.common.dto.ApiResponse;
import sopt.org.FourthSeminar.exception.Error;
import sopt.org.FourthSeminar.exception.model.SoptException;

import java.util.Objects;

/*
Custom한 에러 Response를 적용하여 반환하기 위해 @RestControllerAdvice를 사용하여
모든 Controller에 대해 전역적으로 발생할 수 있는 예외를 잡고 처리한다.

-> @Controller, @RestController 에서 발생하는 예외에만 적용이 가능하다.
*/
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)   // @Valid 어노테이션이 붙은 부분에 대해 예외 발생 시 이를 잡아주는 역할
    protected ApiResponse handlerMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        // 각 필드에서 발생한 에러에 대해 상세 메시지를 처리해줄 수 있다. -> 필드 에러를 발생시킨 값 + 에러 메시지를 반환
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ApiResponse.error(Error.REQUEST_VALIDATION_EXCEPTION, String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    /**
     * 500 Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiResponse<Object> handleException(final Exception e) {
        return ApiResponse.error(Error.INTERNAL_SERVER_ERROR);
    }

    /**
     * Sopt Custom Error
     */
    @ExceptionHandler(SoptException.class)  // 커스텀한 예외 처리 클래스에서 발생한 에러를 잡아주는 역할
    protected ResponseEntity<ApiResponse> handleSoptException(SoptException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getError(), e.getMessage()));
    }
}
