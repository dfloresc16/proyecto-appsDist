package com.appsdist.ms.administracion.model.commons;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Common response.
 */
@Data
public class CommonResponse implements Serializable {
    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 7526471155622776147L;
    /**
     * The Success.
     */
    private boolean success;
    /**
     * The Http status.
     */
    private Integer httpStatus;
    /**
     * The Error code.
     */
    private String errorCode;
    /**
     * The Error message.
     */
    private String errorMessage;
    /**
     * The Message.
     */
    private String message;

    /**
     * Default constructor.
     *
     * @param success      The success flag.
     * @param httpStatus   The http status code.
     * @param errorCode    The error code.
     * @param errorMessage An error message.
     * @param message      A message.
     */
    public CommonResponse(boolean success, Integer httpStatus, String errorCode, String errorMessage, String message) {
        super();
        this.success = success;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.message = message;
    }
}
