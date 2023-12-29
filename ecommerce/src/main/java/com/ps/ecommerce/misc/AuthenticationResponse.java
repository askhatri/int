package com.ps.ecommerce.misc;

public class AuthenticationResponse {

    private final String message;
    private String otp; // For OTP authentication scenarios

    public AuthenticationResponse(String message) {
        this.message = message;
    }

    public AuthenticationResponse(String message, String otp) {
        this.message = message;
        this.otp = otp;
    }

    public String getMessage() {
        return message;
    }

    public String getOtp() {
        return otp;
    }
}