package jpa.cascade.onetomany.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class MemberCascade {

    @Id
    private String id;

    private String name;

}
