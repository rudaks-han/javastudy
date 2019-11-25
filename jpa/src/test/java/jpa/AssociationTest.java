package jpa;

import jpa.manytoone_oneway.entity.MemberType1;
import jpa.manytoone_oneway.entity.TeamType1;
import jpa.manytoone_oneway.repository.MemberType1Repository;
import jpa.manytoone_oneway.repository.TeamType1Repository;
import jpa.manytoone_twoway.entity.MemberType2;
import jpa.manytoone_twoway.entity.TeamType2;
import jpa.manytoone_twoway.repository.MemberType2Repository;
import jpa.manytoone_twoway.repository.TeamType2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociationTest {

    @Autowired
    private MemberType1Repository memberType1Repository;

    @Autowired
    private MemberType2Repository memberType2Repository;

    @Autowired
    private TeamType1Repository teamType1Repository;

    @Autowired
    private TeamType2Repository teamType2Repository;

    @Test
    public void manytoone_oneway_test() {
        TeamType1 team = new TeamType1("research", "연구소");
        teamType1Repository.save(team);

        MemberType1 member = new MemberType1("hong", "홍길동", team);
        memberType1Repository.save(member);
    }

    @Test
    public void manytoone_oneway_retrieve_test() {
        manytoone_oneway_test();

        List<MemberType1> memberType1s = memberType1Repository.findAll();

        /**
         * select * from team
         */
        memberType1s.stream().forEach(member -> {
            System.err.println("member name : " + member.getName());
            System.err.println("member team name : " + member.getTeamType1().getName());
        });

    }

    @Test
    public void manytoone_oneway_update_test() {
        TeamType1 team1 = new TeamType1("research", "연구소");
        teamType1Repository.save(team1);

        Optional<TeamType1> teamType1 = teamType1Repository.findById("research");
        TeamType1 type1 = teamType1.get();
        type1.setName("연구소 이름변경");

        //teamType1Repository.save(teamType3);
    }

    @Test
    public void manytoone_twoway_test() {
        TeamType2 team = new TeamType2("research", "연구소");
        teamType2Repository.save(team);

        MemberType2 member = new MemberType2("hong", "홍길동");
        member.setTeam(team);
        //team.getMembers().add(member); // 이렇게 하면 안된다. 연관관계 주인을 통해 값을 주입해야 한다.

        memberType2Repository.save(member);
    }

    @Test
    public void manytoone_twoway_retrieve_test() {

        manytoone_twoway_test();

        // team정보를 조회한다.
        List<MemberType2> memberTyp2s = memberType2Repository.findAll();

        /**
         * select * from team left outer join member where team_id = ?
         */
        memberTyp2s.stream().forEach(member -> {
            System.err.println("member name : " + member.getName());
            System.err.println("member team name : " + member.getTeam().getName());
        });
    }
}
