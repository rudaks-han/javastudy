package jpa.association.manytoone_twoway.repository;

import jpa.association.manytoone_twoway.entity.MemberType2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberType2Repository extends JpaRepository<MemberType2, String> {
}
