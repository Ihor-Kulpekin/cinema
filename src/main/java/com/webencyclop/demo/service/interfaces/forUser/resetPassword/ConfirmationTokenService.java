package com.webencyclop.demo.service.interfaces.forUser.resetPassword;

import com.webencyclop.demo.model.resetPassword.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    void save(ConfirmationToken confirmationToken);
}
