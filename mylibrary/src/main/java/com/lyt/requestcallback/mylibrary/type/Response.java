package com.lyt.requestcallback.mylibrary.type;

public class Response {
    private String requestType;
    private boolean success;
    private String message;

    public Response(String requestType, boolean success, String message) {
        this.requestType = requestType;
        this.success = success;
        this.message = message;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void isNull(){

    }

    @Override
    public String toString() {
        return "requestType:["+requestType+"]success:["+success+"]message:["+message+"]";
    }
}
