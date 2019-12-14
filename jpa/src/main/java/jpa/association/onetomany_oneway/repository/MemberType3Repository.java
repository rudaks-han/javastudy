package jpa.association.onetomany_oneway.repository;

import jpa.association.onetomany_oneway.entity.MemberType3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface MemberType3Repository extends JpaRepository<MemberType3, String> {
}
