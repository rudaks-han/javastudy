package jpa.association.manytoone_oneway.repository;

import jpa.association.manytoone_oneway.entity.Member1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Member1Repository extends JpaRepository<Member1, String> {
}
