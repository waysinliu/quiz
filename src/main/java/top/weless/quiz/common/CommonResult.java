package top.weless.quiz.common;

import lombok.Data;

@Data
public class CommonResult<T> {
    private boolean state;
    private String msg;
    private T data;

    private CommonResult(boolean state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(true, null, data);
    }

    public static <T> CommonResult<T> fail(String msg) {
        return new CommonResult<>(false, msg, null);
    }
}
