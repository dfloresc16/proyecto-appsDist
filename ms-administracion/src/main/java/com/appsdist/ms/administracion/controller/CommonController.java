package com.appsdist.ms.administracion.controller;

/**
 * The type Common controller.
 */
public class CommonController {
    /**
     * The Shared key.
     */
    private final String sharedKey = "SHARED_KEY";
    /**
     * The constant SUCCESS.
     */
    public static final boolean SUCCESS = true;
    /**
     * The constant ERROR.
     */
    public static final boolean ERROR = false;
    /**
     * The constant HTTP_SUCCESS.
     */
    public static final int HTTP_SUCCESS = 200;
    /**
     * The constant HTTP_NOT_CONTENT.
     */
    public static final int HTTP_NOT_CONTENT = 204;
    /**
     * The constant HTTP_BAD_REQUEST.
     */
    public static final int HTTP_BAD_REQUEST = 400;
    /**
     * The constant HTTP_AUTH_FAILURE.
     */
    public static final int HTTP_AUTH_FAILURE = 401;
    /**
     * The constant HTTP_NOT_FOUND.
     */
    public static final int HTTP_NOT_FOUND = 404;
    /**
     * The constant HTTP_APP_FAILURE.
     */
    public static final int HTTP_APP_FAILURE = 500;
    /**
     * The constant HTTP_APP_NOT_IMPL.
     */
    public static final int HTTP_APP_NOT_IMPL = 501;
}
