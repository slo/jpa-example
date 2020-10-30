package sl.testapp.jpatest.member;

import org.springframework.data.jpa.repository.JpaRepository;

//@PersCont
//priv EM em

//@Produces
//@AppScop
//new JpaRepo(entityManag).getRepo(class);
public interface MemberRepository extends JpaRepository<Member, Long> {

}
