package com.createvision.sivilima.valuesObject;

public class ReturnVO {
    private int statusCode;
    private boolean success;
    private Object result;
    private String message;

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public Object getResult() { return result; }
    public void setResult(Object result) { this.result = result; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message;}
}


