package jpa.association.manytoone_oneway.repository;

import jpa.association.manytoone_oneway.entity.Team1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Team1Repository extends JpaRepository<Team1, String> {
}
