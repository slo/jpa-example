package sl.testapp.jpatest.member;

import java.util.List;

//@Transactional
public class MemberDAO {

	// @Inject
	private MemberRepository memberRepository;

	public List<Member> findAll() {
		return null;// memberRepository.findAll();
	}
}
