package jpa.orphanremoval;


import jpa.orphanremoval.onetomany.MemberOrphanRemoval;
import jpa.orphanremoval.onetomany.TeamOrphanRemoval;
import jpa.orphanremoval.onetomany.repository.MemberOrphanRemovalRepository;
import jpa.orphanremoval.onetomany.repository.TeamOrphanRemovalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrphanRemovalTest {

    @Autowired
    private TeamOrphanRemovalRepository teamOrphanRemovalRepository;

    @Autowired
    private MemberOrphanRemovalRepository memberOrphanRemovalRepository;

    @Test
    public void orphanRemoval_test() {

        MemberOrphanRemoval member = new MemberOrphanRemoval("hong", "홍길동");
        memberOrphanRemovalRepository.save(member);

        TeamOrphanRemoval team = new TeamOrphanRemoval("research", "연구소");
        team.addMember(member);
        teamOrphanRemovalRepository.save(team);

        teamOrphanRemovalRepository.delete(team);
    }

}
