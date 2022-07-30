package top.weless.quiz.common;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public CommonResult<String> CommomExceptionHandler(CommonException e) {
        return CommonResult.fail(e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public CommonResult<String> DuplicateEmail(SQLIntegrityConstraintViolationException e) {
        return CommonResult.fail("邮箱已被注册.");
    }
}
