package jpa.association.manytoone_oneway.repository;

import jpa.association.manytoone_oneway.entity.MemberType1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberType1Repository extends JpaRepository<MemberType1, String> {
}