package jpa.association.onetomany_oneway;

import jpa.association.onetomany_oneway.entity.Member3;
import jpa.association.onetomany_oneway.entity.Team3;
import jpa.association.onetomany_oneway.repository.Member3Repository;
import jpa.association.onetomany_oneway.repository.Team3Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class oneToManyOnewayTest {

    @Autowired
    private Member3Repository memberRepository;

    @Autowired
    private Team3Repository teamRepository;

    @Test
    public void manytoone_oneway_test() {

        Team3 team = new Team3("research", "연구소");
        team.addMember(new Member3("hong1", "홍길동1"));
        team.addMember(new Member3("hong2", "홍길동2"));
        team.addMember(new Member3("hong3", "홍길동3"));

        teamRepository.save(team);

        /**
         * 총 7번의 쿼리 실행
         * insert team
         * insert member
         * insert member
         * insert member
         * update member
         * update member
         * update member
          */


        Member3 member3 = memberRepository.findById("hong1").get();
        team.getMembers().remove(member3);

        teamRepository.save(team);

        /**
         * update member (member3의 team_id를 null로 update)
         * delete member
         */

        /*team.getMembers().remove(member3);*/


    }

    /*@Test
    public void manytoone_oneway_retrieve_test() {
        manytoone_oneway_test();

        List<Member4> memberList = memberRepository.findAll();

        // MemberType1에서 TeamType1으로 ManyToOne 연관관계가 있으므로 Team정보를 조회한다.
        System.err.println("# member");
        memberList.stream().forEach(member -> {
            System.err.println("name : " + member.getName());
            System.err.println("team name : " + member.getTeam1().getName());
        });

        // Oneway라서 Team에는 Member의 정보가 없다.
    }

    @Test
    public void manytoone_oneway_update_test() {
        Team1 team1 = new Team1("research", "연구소");
        teamRepository.save(team1);

        team1.setName("연구소 이름변경");
        teamRepository.save(team1);
    }*/
}
