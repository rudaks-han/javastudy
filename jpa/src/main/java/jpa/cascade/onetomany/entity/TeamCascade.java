package jpa.cascade.onetomany.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class TeamCascade {

    @Id
    private String id;

    private String name;

    public TeamCascade(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private List<MemberCascade> members = new ArrayList<>();

    public void addMember(MemberCascade member) {
        this.members.add(member);
    }
}
