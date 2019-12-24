package jpa.association.onetomany_twoway.repository;

import jpa.association.onetomany_twoway.entity.Team4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Team4Repository extends JpaRepository<Team4, String> {
}
