package com.ps.ecommerce.services;

import com.ps.ecommerce.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OtpService.class);
    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRY_TIME_MINUTES = 5;
    private final Random random = new Random();
    @Autowired
    UserService userService;

    public String generateOtp(String phone) {
        return generateRandomNumberString();
    }

    public void sendOtp(String phone) {
        User user = userService.getUserByPhone(phone);

        if (user.getPhone() != null && user.getOtp() != null) {
            sendOtpViaSms(user.getPhone(), user.getOtp());
            return;
        }

        throw new IllegalStateException("No contact information found for user");
    }

    private void sendOtpViaSms(String phoneNumber, String otp) {
        logger.info("Sending OTP via SMS to: " + phoneNumber + ", OTP: " + otp);
    }

    private String generateRandomNumberString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < OtpService.OTP_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
