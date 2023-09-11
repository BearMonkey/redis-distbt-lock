package org.monkey.distbtlock.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private String code;
    private Object data;
    private String msg;

    public static Result success() {
        return new Result("200", null, "");
    }
    public Result result(String code, Object data, String msg) {
        return new Result(code, data, msg);
    }

}
