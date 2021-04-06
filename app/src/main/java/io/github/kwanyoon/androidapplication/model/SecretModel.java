package io.github.kwanyoon.androidapplication.model;

public class SecretModel {
    private int secretId, secretStatus;
    private String placeholder, secret;

    public int getSecretId() {
        return secretId;
    }

    public void setSecretId(int secretId) {
        this.secretId = secretId;
    }

    public int getSecretStatus() {
        return secretStatus;
    }

    public void setSecretStatus(int secretStatus) {
        this.secretStatus = secretStatus;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
