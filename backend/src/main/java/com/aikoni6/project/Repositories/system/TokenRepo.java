package com.aikoni6.project.Repositories.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.system.Token;

import java.util.List;

public interface TokenRepo extends JpaRepository<Token, Long> {
    public List<Token> findByPublicPart(String publicPart);
    public List<Token> findByPrivatePart(String privatePart);
    public List<Token> findByProfile(Long profile);
}
