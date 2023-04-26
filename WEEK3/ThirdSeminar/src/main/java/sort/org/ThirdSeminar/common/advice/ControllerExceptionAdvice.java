package sort.org.ThirdSeminar.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sort.org.ThirdSeminar.common.dto.ApiResponseDto;
import sort.org.ThirdSeminar.exception.ErrorStatus;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponseDto handlerMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ApiResponseDto.error(ErrorStatus.VALIDATION_REQUEST_MISSING_EXCEPTION);
    }
}
