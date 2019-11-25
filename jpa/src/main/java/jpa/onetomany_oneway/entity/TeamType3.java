package jpa.onetomany_oneway.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class TeamType3 {

    @Id
    private String id;

    private String name;

    @OneToMany
    @JoinColumn(name = "team_id")
    private List<MemberType3> members = new ArrayList<>();
}
