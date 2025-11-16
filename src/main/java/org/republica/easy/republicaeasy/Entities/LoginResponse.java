package org.republica.easy.republicaeasy.Entities;

public class LoginResponse {
    private String token;
    private String error;
    private Long ExpiresIn;

    public LoginResponse() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresIn() {
        return ExpiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        ExpiresIn = expiresIn;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
