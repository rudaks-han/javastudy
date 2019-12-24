package jpa.association.manytoone_twoway;

import jpa.association.manytoone_twoway.entity.Member2;
import jpa.association.manytoone_twoway.entity.Team2;
import jpa.association.manytoone_twoway.repository.Member2Repository;
import jpa.association.manytoone_twoway.repository.Team2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToOneTwowayTest {

    @Autowired
    private Member2Repository member2Repository;

    @Autowired
    private Team2Repository team2Repository;

    @Test
    public void manytoone_twoway_test() {
        Team2 team = new Team2("research", "연구소");
        team2Repository.save(team);

        Member2 member = new Member2("hong", "홍길동");
        member.setTeam(team);
        //team.getMembers().add(member); // 이렇게 하면 안된다. 연관관계 주인을 통해 값을 주입해야 한다.

        member2Repository.save(member);
    }

    @Test
    public void manytoone_twoway_retrieve_test() {

        manytoone_twoway_test();

        // member정보를 조회한다.
        List<Member2> member2List = member2Repository.findAll();

        /**
         * select * from team left outer join member where team_id = ?
         */
        System.err.println("# member");
        member2List.stream().forEach(member -> {
            System.err.println("name : " + member.getName());
            System.err.println("team name : " + member.getTeam().getName());
        });

        // member정보를 조회한다.
        List<Team2> team2List = team2Repository.findAll();

        System.err.println("# team");
        team2List.stream().forEach(team2 -> {
            System.err.println("name : " + team2.getName());
            System.err.println("team name : " + team2.getMembers());
        });

    }
}
