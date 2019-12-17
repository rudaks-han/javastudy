package jpa.association.onetomany_oneway.entity;

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
public class Team3 {

    @Id
    private String id;

    private String name;

    public Team3(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMember(Member3 member3) {
        this.members.add(member3);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "team_id")
    private List<Member3> members = new ArrayList<>();
}
