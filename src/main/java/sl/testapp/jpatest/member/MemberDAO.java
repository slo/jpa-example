package sl.testapp.jpatest.member;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
public class MemberDAO {

	@Inject
	private MemberRepository memberRepository;

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	public List<Member> findByName(String name) {
		return memberRepository.findByName(name);
	}
}
