package jpa.association.onetomany_twoway.entity;

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
@EqualsAndHashCode
public class Team4 {

    @Id
    private String id;

    private String name;

    public Team4(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMember(Member4 member4) {
        this.members.add(member4);
        member4.setTeam(this);
    }

    public void removeMember(Member4 member4) {
        this.members.remove(member4);
        member4.setTeam(null);
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member4> members = new ArrayList<>();
}
