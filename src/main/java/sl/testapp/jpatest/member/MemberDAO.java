package sl.testapp.jpatest.member;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

//@Transactional
@ApplicationScoped
public class MemberDAO {

	@Inject
	private MemberRepository memberRepository;

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	public List<Member> findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
}
