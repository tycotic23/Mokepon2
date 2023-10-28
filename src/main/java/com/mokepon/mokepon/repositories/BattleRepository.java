package com.mokepon.mokepon.repositories;

import com.mokepon.mokepon.models.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BattleRepository extends JpaRepository<Battle,Long> {
}
