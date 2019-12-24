package jpa.association.manytoone_twoway.repository;

import jpa.association.manytoone_twoway.entity.Team2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Team2Repository extends JpaRepository<Team2, String> {
}
