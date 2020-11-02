package sl.testapp.jpatest.member;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class MemberDAO {

	@Inject
	private MemberRepository memberRepository;

	public List<Member> findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
}
