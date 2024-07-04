package com.BlogApp.payloads;

public class ApiResponse {
    private String message;
    private boolean success;
    private int code;

    public ApiResponse(String message, boolean success, int code) {
        this.message = message;
        this.success = success;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
