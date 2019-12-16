package jpa.orphanremoval.onetomany;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class MemberOrphanRemoval {

    @Id
    private String id;

    private String name;

}
