package sl.testapp.jpatest;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import sl.testapp.jpatest.member.MemberRepository;

@Singleton
@Startup
public class EagerRepositories {

	@Inject
	private MemberRepository memberRepository;

	@PostConstruct
	public void initializeRepo() {
		memberRepository.toString();
	}
}
