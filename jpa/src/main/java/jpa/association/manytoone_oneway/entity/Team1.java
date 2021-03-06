package jpa.association.manytoone_oneway.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Team1 {

    @Id
    private String id;

    private String name;
}
