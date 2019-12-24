package jpa.association.onetomany_oneway.repository;

import jpa.association.onetomany_oneway.entity.Team3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Team3Repository extends JpaRepository<Team3, String> {
}
