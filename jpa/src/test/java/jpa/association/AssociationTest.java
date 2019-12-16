package jpa.association;

import jpa.association.manytoone_oneway.entity.Member1;
import jpa.association.manytoone_oneway.entity.Team1;
import jpa.association.manytoone_oneway.repository.Member1Repository;
import jpa.association.manytoone_oneway.repository.Team1Repository;
import jpa.association.manytoone_twoway.entity.Member2;
import jpa.association.manytoone_twoway.entity.Team2;
import jpa.association.manytoone_twoway.repository.Member2Repository;
import jpa.association.manytoone_twoway.repository.Team2Repository;
import jpa.association.onetomany_oneway.entity.Member3;
import jpa.association.onetomany_oneway.entity.Team3;
import jpa.association.onetomany_oneway.repository.Member3Repository;
import jpa.association.onetomany_oneway.repository.Team3Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociationTest {

    @Autowired
    private Member1Repository member1Repository;

    @Autowired
    private Member2Repository member2Repository;

    @Autowired
    private Team1Repository team1Repository;

    @Autowired
    private Team2Repository team2Repository;

    @Autowired
    private Team3Repository team3Repository;

    @Autowired
    private Member3Repository member3Repository;

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

        List<Member1> member1s = member1Repository.findAll();

        /**
         * select * from team
         */
        member1s.stream().forEach(member -> {
            System.err.println("member name : " + member.getName());
            System.err.println("member team name : " + member.getTeam1().getName());
        });

    }

    @Test
    public void manytoone_oneway_update_test() {
        Team1 team1 = new Team1("research", "연구소");
        team1Repository.save(team1);

        Optional<Team1> teamType1 = team1Repository.findById("research");
        Team1 type1 = teamType1.get();
        type1.setName("연구소 이름변경");

        //teamType1Repository.save(teamType3);
    }

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

        // team정보를 조회한다.
        List<Member2> memberTyp2s = member2Repository.findAll();

        /**
         * select * from team left outer join member where team_id = ?
         */
        memberTyp2s.stream().forEach(member -> {
            System.err.println("member name : " + member.getName());
            System.err.println("member team name : " + member.getTeam().getName());
        });
    }

    @Test
    public void onetomany_oneway_test() {
        Member3 member = new Member3("hong", "홍길동");
        member3Repository.save(member);

        Team3 team = new Team3("research", "연구소", Arrays.asList(member));
        team3Repository.save(team);


    }
}
