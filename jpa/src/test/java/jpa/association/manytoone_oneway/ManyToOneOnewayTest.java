package jpa.association.manytoone_oneway;

import jpa.association.manytoone_oneway.entity.Member1;
import jpa.association.manytoone_oneway.entity.Team1;
import jpa.association.manytoone_oneway.repository.Member1Repository;
import jpa.association.manytoone_oneway.repository.Team1Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToOneOnewayTest {

    @Autowired
    private Member1Repository member1Repository;

    @Autowired
    private Team1Repository team1Repository;

    @Test
    public void manytoone_oneway_test() {
        Team1 team = new Team1("research", "연구소");
        team1Repository.save(team);

        Member1 member = new Member1("hong", "홍길동", team);
        member1Repository.save(member);
    }

    @Test
    public void manytoone_oneway_retrieve_test() {
        manytoone_oneway_test();

        List<Member1> member1List = member1Repository.findAll();

        // MemberType1에서 TeamType1으로 ManyToOne 연관관계가 있으므로 Team정보를 조회한다.
        System.err.println("# member");
        member1List.stream().forEach(member -> {
            System.err.println("name : " + member.getName());
            System.err.println("team name : " + member.getTeam1().getName());
        });

        // Oneway라서 Team에는 Member의 정보가 없다.
    }

    @Test
    public void manytoone_oneway_update_test() {
        Team1 team1 = new Team1("research", "연구소");
        team1Repository.save(team1);

        team1.setName("연구소 이름변경");
        team1Repository.save(team1);
    }
}
