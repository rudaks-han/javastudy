package jpa.association.manytoone_twoway.repository;

import jpa.association.manytoone_twoway.entity.Member2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Member2Repository extends JpaRepository<Member2, String> {
}
