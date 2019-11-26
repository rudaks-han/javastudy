package jpa.association.manytoone_oneway.repository;

import jpa.association.manytoone_oneway.entity.TeamType1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamType1Repository extends JpaRepository<TeamType1, String> {
}