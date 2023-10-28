package com.mokepon.mokepon.repositories;

import com.mokepon.mokepon.models.AttackPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AttackPlayerRepository extends JpaRepository<AttackPlayer,Long> {
}
