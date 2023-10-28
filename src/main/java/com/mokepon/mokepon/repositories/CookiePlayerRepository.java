package com.mokepon.mokepon.repositories;

import com.mokepon.mokepon.models.CookiePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CookiePlayerRepository extends JpaRepository<CookiePlayer,Long> {
    boolean existsByPlayer_name(String name);
    CookiePlayer findByPlayer_name(String name);
}
