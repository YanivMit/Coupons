package com.example.CouponsExample.Entities.Repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CouponsExample.Entities.beans.Token;
import com.example.CouponsExample.Login.ClientType;

@Repository
public interface TokenRepo extends JpaRepository<Token, Integer> {

    Optional<Token> findByToken(String token);

    Optional<Token> findByClientTypeAndToken(ClientType clientType, String token);

    @Modifying
    @Transactional
    void deleteAllByClientTypeAndUserId(ClientType clientType, int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Token t WHERE t.expDate < CURRENT_DATE")
    void deleteExpiredTokens();
}