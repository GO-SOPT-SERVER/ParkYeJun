package sort.org.ThirdSeminar.common.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sort.org.ThirdSeminar.exception.ErrorStatus;

@Getter
@AllArgsConstructor
public class BaseException extends Exception {
    private ErrorStatus status;
}
