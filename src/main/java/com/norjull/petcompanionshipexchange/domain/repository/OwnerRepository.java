package com.norjull.petcompanionshipexchange.domain.repository;

import com.norjull.petcompanionshipexchange.domain.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
