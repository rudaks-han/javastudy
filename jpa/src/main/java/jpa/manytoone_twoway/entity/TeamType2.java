package jpa.manytoone_twoway.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class TeamType2 {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<MemberType2> members = new ArrayList<>();

    public TeamType2(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /*public String toString() {
        return JsonUtil.toJson(this);
    }*/
}
