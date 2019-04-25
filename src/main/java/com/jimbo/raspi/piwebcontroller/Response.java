package com.jimbo.raspi.piwebcontroller;

public class Response {

    private String gpioState;
    private String gpioID;

    private String errorMessage;

    public Response(String gpioID, String gpioState) {
        this.gpioID = gpioID;
        this.gpioState = gpioState;
    }

    public Response(String errorMessage) {
        this.errorMessage = "Exception, " + errorMessage;
    }

    public String getGpioState() {
        return gpioState;
    }

    public void setGpioState(String gpioState) {
        this.gpioState = gpioState;
    }

    public String getGpioID() {
        return gpioID;
    }

    public void setGpioID(String gpioID) {
        this.gpioID = gpioID;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
