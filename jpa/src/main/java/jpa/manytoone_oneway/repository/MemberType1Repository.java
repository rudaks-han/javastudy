package jpa.manytoone_oneway.repository;

import jpa.manytoone_oneway.entity.MemberType1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberType1Repository extends JpaRepository<MemberType1, String> {
}
