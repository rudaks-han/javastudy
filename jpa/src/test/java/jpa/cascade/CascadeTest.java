package jpa.cascade;


import jpa.cascade.onetomany.entity.MemberCascade;
import jpa.cascade.onetomany.entity.TeamCascade;
import jpa.cascade.onetomany.repository.TeamCascadeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CascadeTest {

    @Autowired
    private TeamCascadeRepository teamCascadeRepository;

    @Test
    public void cascade_persist_test() {

        TeamCascade team = new TeamCascade("research", "연구소");
        MemberCascade member = new MemberCascade("hong", "홍길동");
        team.addMember(member);

        teamCascadeRepository.save(team);
    }

    @Test
    public void cascade_remove_test() {

        TeamCascade team = new TeamCascade("research", "연구소");
        MemberCascade member = new MemberCascade("hong", "홍길동");
        team.addMember(member);

        teamCascadeRepository.save(team);

        teamCascadeRepository.delete(team);
    }
}
