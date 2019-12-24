package jpa.association.manytoone_twoway.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Member2 {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team2 team;

    public Member2(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setTeam(Team2 team) {

        if (this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this);
    }

    /*public String toString() {
        return JsonUtil.toJson(this);
    }*/
}
