package com.xp.client.utils;

import com.xp.client.enums.IRspCode;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 4290784890876524392L;
    private long timestamp;
    private String rspCode;
    private String rspMsg;
    private String errorCode;
    private String errorMsg;
    private String errorDesc;
    private String errorLevel = "1";

    public BaseException() {
    }

    /** @deprecated */
    @Deprecated
    public BaseException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorDesc = errorMsg;
    }

    public BaseException(IRspCode rspCode, String errorMsg) {
        super(errorMsg);
        this.rspCode = rspCode.getCode();
        this.rspMsg = rspCode.getMsg();
        this.errorCode = rspCode.getCode();
        this.errorMsg = errorMsg;
        this.errorDesc = errorMsg;
    }

    /** @deprecated */
    @Deprecated
    public BaseException(String errorMsg, String errorCode) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorDesc = errorMsg;
    }

    /** @deprecated */
    @Deprecated
    public BaseException(String errorMsg, Throwable throwable) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorDesc = throwable.getMessage();
    }

    /** @deprecated */
    @Deprecated
    public BaseException(String errorMsg, String errorCode, Throwable throwable) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorDesc = throwable.getMessage();
    }

    public BaseException(String rspCode, String rspMsg, String errorMsg) {
        super(errorMsg);
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
        this.errorCode = rspCode;
        this.errorMsg = errorMsg;
        this.errorDesc = errorMsg;
    }

    public BaseException(BaseException e, long timestamp) {
        super(e);
        this.rspCode = e.getRspCode();
        this.rspMsg = e.getRspMsg();
        this.errorCode = e.getErrorCode();
        this.errorMsg = e.getErrorMsg();
        this.errorDesc = e.getErrorDesc();
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getRspCode() {
        return this.rspCode;
    }

    public String getRspMsg() {
        return this.rspMsg;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public String getErrorLevel() {
        return this.errorLevel;
    }
}
