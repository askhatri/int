package com.ps.ecommerce.misc;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private static final int OTP_LENGTH = 6; // Number of digits in the OTP
    private static final int OTP_EXPIRY_TIME_MINUTES = 5; // OTP validity duration

    private final Random random = new Random();
    private final Map<String, OtpDetails> otpMap = new HashMap<>(); // Stores OTPs with expiry

    public String generateOtp(String username) {
        String otp = generateRandomNumberString(OTP_LENGTH);
        otpMap.put(username, new OtpDetails(otp, LocalDateTime.now().plusMinutes(OTP_EXPIRY_TIME_MINUTES)));
        return otp;
    }

    public void sendOtp(String username, String otp) {
        String email = retrieveUserEmail(username); // Assuming a method to fetch user's email
        String phoneNumber = retrieveUserPhoneNumber(username); // Assuming a method to fetch user's phone number

        // Choose appropriate delivery method(s) based on available contact information:
        if (email != null) {
            sendOtpViaEmail(email, otp);
        }

        if (phoneNumber != null) {
            sendOtpViaSms(phoneNumber, otp);
        }

        // If no contact information is available, handle the error gracefully:
        if (email == null && phoneNumber == null) {
            throw new IllegalStateException("No contact information found for user");
        }
    }

    private String retrieveUserPhoneNumber(String username) {
        return "9988776655";
    }

    private String retrieveUserEmail(String username) {
        return "username@ps.com";
    }

// Methods for specific delivery methods:

    private void sendOtpViaEmail(String email, String otp) {
        // Use an email library or service to send the OTP to the specified email address
        // Example using a placeholder:
        System.out.println("Sending OTP via email to: " + email + ", OTP: " + otp);
    }

    private void sendOtpViaSms(String phoneNumber, String otp) {
        // Use an SMS gateway or service to send the OTP to the specified phone number
        // Example using a placeholder:
        System.out.println("Sending OTP via SMS to: " + phoneNumber + ", OTP: " + otp);
    }

    public boolean validateOtp(String username, String otp) {
        OtpDetails otpDetails = otpMap.get(username);
        if (otpDetails == null || !otpDetails.getOtp().equals(otp)) {
            return false; // Invalid OTP
        }

        // Check expiry
        if (otpDetails.getExpiryTime().isBefore(LocalDateTime.now())) {
            otpMap.remove(username);
            return false; // OTP expired
        }

        otpMap.remove(username); // OTP validated, remove it
        return true;
    }

    private String generateRandomNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // Helper class to store OTP and expiry details
    private static class OtpDetails {
        private final String otp;
        private final LocalDateTime expiryTime;

        public OtpDetails(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpiryTime() {
            return expiryTime;
        }
    }
}