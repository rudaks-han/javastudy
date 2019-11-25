package jpa.onetomany_oneway.repository;

import jpa.onetomany_oneway.entity.TeamType3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamType3Repository extends JpaRepository<TeamType3, String> {
}
