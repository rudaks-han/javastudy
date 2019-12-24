package jpa.association.onetomany_twoway.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Member4 {

    @Id
    private String id;

    private String name;

    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "team_id")
    private Team4 team;
}
