package jpa.orphanremoval.onetomany.repository;

import jpa.orphanremoval.onetomany.TeamOrphanRemoval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamOrphanRemovalRepository extends JpaRepository<TeamOrphanRemoval, String> {
}
