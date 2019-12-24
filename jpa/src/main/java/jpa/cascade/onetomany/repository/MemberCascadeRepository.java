package jpa.cascade.onetomany.repository;

import jpa.cascade.onetomany.entity.MemberCascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCascadeRepository extends JpaRepository<MemberCascade, String> {
}
