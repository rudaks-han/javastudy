package jpa.manytoone_twoway.repository;

import jpa.manytoone_twoway.entity.TeamType2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamType2Repository extends JpaRepository<TeamType2, String> {
}
