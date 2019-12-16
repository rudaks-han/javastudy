package jpa.orphanremoval.onetomany.repository;

import jpa.orphanremoval.onetomany.MemberOrphanRemoval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberOrphanRemovalRepository extends JpaRepository<MemberOrphanRemoval, String> {
}
