package com.example.CouponsExample.Entities.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.CouponsExample.Login.ClientType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@SuppressWarnings("serial")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Token implements Serializable, Comparable<Token> {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expDate;

    @Column(nullable = false)
    private String token;

    public Token() {
    }

    public Token(ClientType clientType,
                 Date expDate,
                 String token) {
        this.clientType = clientType;
        this.expDate = expDate;
        this.token = token;
    }

    public Token(int userId,
                 ClientType clientType,
                 Date expDate,
                 String token) {
        this(clientType, expDate, token);
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int compareTo(Token o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Token)) {
            return false;
        }
        Token other = (Token) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", userId=" + userId +
                ", clientType=" + clientType +
                ", expDate=" + expDate +
                ", token='" + token + '\'' +
                '}';
    }
}

