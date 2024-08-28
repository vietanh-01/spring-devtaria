package com.spring.identity_service.repository;

import com.spring.identity_service.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvalidatedTokenRepo extends JpaRepository<InvalidatedToken, String> {
}
