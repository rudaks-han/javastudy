package jpa.association.onetomany_twoway.repository;

import jpa.association.onetomany_twoway.entity.Member4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Member4Repository extends JpaRepository<Member4, String> {
}
