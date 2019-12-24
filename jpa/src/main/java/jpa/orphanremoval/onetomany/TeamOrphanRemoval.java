package jpa.orphanremoval.onetomany;

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
public class TeamOrphanRemoval {

    @Id
    private String id;

    private String name;

    public TeamOrphanRemoval(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "team_id")
    private List<MemberOrphanRemoval> members = new ArrayList<>();

    public void addMember(MemberOrphanRemoval member) {
        this.members.add(member);
    }
}
