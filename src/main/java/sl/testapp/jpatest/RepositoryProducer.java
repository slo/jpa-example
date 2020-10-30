package sl.testapp.jpatest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class RepositoryProducer {

	@Produces
	@Dependent
	@PersistenceContext
	private EntityManager em;


	/*
	 * @Produces
	 * 
	 * @ApplicationScoped public MemberRepository getMemberRepository() { return new
	 * JpaRepositoryFactory(em).getRepository(MemberRepository.class); }
	 * 
	 * public void close(@Disposes EntityManager entityManager) {
	 * entityManager.close(); }
	 */
}
