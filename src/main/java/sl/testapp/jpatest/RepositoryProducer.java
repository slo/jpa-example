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
}
