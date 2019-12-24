package jpa.cascade.onetomany.repository;

import jpa.cascade.onetomany.entity.TeamCascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamCascadeRepository extends JpaRepository<TeamCascade, String> {
}
