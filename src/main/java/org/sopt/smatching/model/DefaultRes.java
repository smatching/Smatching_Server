package org.sopt.smatching.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;

@Data
@Builder
@AllArgsConstructor
public class DefaultRes<T> {

    private int status;

    private String message;

    private T data;

    public DefaultRes(final int status, final String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static <T> DefaultRes<T> res(final int status, final String message) {
        return res(status, message, null);
    }

    public static <T> DefaultRes<T> res(final int status, final String message, final T t) {
        return DefaultRes.<T>builder()
                .data(t)
                .status(status)
                .message(message)
                .build();
    }

    public static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
    public static final DefaultRes FAIL_DEFAULT_RES_IN_CONTROLLER = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR_IN_CONTROLLER);

}
