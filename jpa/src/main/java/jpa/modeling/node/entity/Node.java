package jpa.modeling.node.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Node {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private Node parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Node> children = new ArrayList<>();

    public Node(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addChild(Node node) {
        this.children.add(node);
    }
}
