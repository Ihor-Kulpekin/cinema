package com.webencyclop.demo.service.interfaces.forUser;

import com.webencyclop.demo.model.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    void save(ConfirmationToken confirmationToken);
}
