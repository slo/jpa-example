package sl.testapp.jpatest.member;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

@Transactional(rollbackOn = { Exception.class })
@ApplicationScoped
public class MemberDAO {

	@Inject
	private MemberRepository memberRepository;

	@Resource
	private UserTransaction ut;

	public List<Member> findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	public List<Member> performManyOperationsAndOptionallyThrowExc(String email, boolean throwException) throws Exception {

		Member m = new Member();
		m.setId(4L);
		m.setAddress("moj addresss");
		m.setEmail(email);
		m.setName("Mieczyslaw");
		m.setPhoneNumber("555678678");
		memberRepository.save(m);
		// em.flush();
		if (throwException) {
			throw new Exception("maj mesydz");
		}
		return memberRepository.findAll();
	}

}
