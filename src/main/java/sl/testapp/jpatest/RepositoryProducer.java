package sl.testapp.jpatest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import sl.testapp.jpatest.member.MemberRepository;

@ApplicationScoped
public class RepositoryProducer {

	@Produces
	@Dependent
	@PersistenceContext
	private EntityManager em;

	@Produces
	@ApplicationScoped
	public MemberRepository getMemberRepository() {
		return new JpaRepositoryFactory(em).getRepository(MemberRepository.class);
	}

}
