package jpa.association.onetomany_twoway;

import jpa.association.onetomany_twoway.entity.Member4;
import jpa.association.onetomany_twoway.entity.Team4;
import jpa.association.onetomany_twoway.repository.Member4Repository;
import jpa.association.onetomany_twoway.repository.Team4Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class oneToManyTwowayTest {

    @Autowired
    private Member4Repository memberRepository;

    @Autowired
    private Team4Repository teamRepository;

    @Test
    public void oneTwoMany_twoway_test() {

        Team4 team = new Team4("research", "연구소");
        team.addMember(new Member4("hong1", "홍길동1", team));
        team.addMember(new Member4("hong2", "홍길동2", team));
        team.addMember(new Member4("hong3", "홍길동3", team));

        teamRepository.save(team);

        /**
         * 총 4번의 쿼리 실행
         * insert team
         * insert member
         * insert member
         * insert member
          */

        Member4 member = memberRepository.findById("hong1").get();
        team.removeMember(member);
        //teamRepository.save(team);

        //memberRepository.delete(member);
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
