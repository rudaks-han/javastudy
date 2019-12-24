package jpa.association.onetomany_oneway.repository;

import jpa.association.onetomany_oneway.entity.Member3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Member3Repository extends JpaRepository<Member3, String> {
}
