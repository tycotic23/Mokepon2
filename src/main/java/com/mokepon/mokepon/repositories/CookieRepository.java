package com.mokepon.mokepon.repositories;

import com.mokepon.mokepon.models.Cookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CookieRepository extends JpaRepository<Cookie,Long> {
}
