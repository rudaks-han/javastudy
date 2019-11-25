package jpa.manytoone_oneway.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class MemberType1 {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamType1 teamType1;

}
