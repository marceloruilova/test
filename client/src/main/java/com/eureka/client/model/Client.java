package com.eureka.client.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Client")
public class Client extends Person {

    @Column(name = "client_id", nullable = false, unique = true, length = 50)
    private String clientId;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "state", nullable = false, length = 20)
    private String state;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}