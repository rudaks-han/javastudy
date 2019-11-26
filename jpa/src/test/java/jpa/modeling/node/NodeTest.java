package jpa.modeling.node;

import jpa.modeling.node.entity.Node;
import jpa.modeling.node.repository.NodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeTest {

    @Autowired
    private NodeRepository nodeRepository;

    @Test
    public void test() {

        Node parent = new Node("parent_id", "부모노드");
        Node child = new Node("child_id1", "자식노드1");

        List<Node> children = new ArrayList<>();
        children.add(child);
        parent.setChildren(children);
        child.setParent(parent);

        nodeRepository.save(parent);
    }

    @Test
    public void test2() {

        Node parent = new Node("parent_id", "부모노드");
        Node child1 = new Node("child_id1", "자식노드1");
        Node child2 = new Node("child_id2", "자식노드2");

        List<Node> children1 = new ArrayList<>();
        children1.add(child1);
        children1.add(child2);

        parent.setChildren(children1);
        child1.setParent(parent);
        child2.setParent(parent);

        nodeRepository.save(parent);
    }

    @Test
    public void test3() {

        Node parent = new Node("parent_id", "부모노드");
        Node child1 = new Node("child_id1", "자식노드1");
        Node child21 = new Node("child_id2-1", "자식노드2-1");

        child1.addChild(child21);
        parent.addChild(child1);

        child21.setParent(child1);
        child1.setParent(parent);

        nodeRepository.save(parent);

        List<Node> nodes = nodeRepository.findAll();
    }
}
