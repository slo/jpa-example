package sl.testapp.jpatest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Test;
import org.junit.runner.RunWith;

import sl.testapp.jpatest.member.Member;
import sl.testapp.jpatest.member.MemberDAO;
import sl.testapp.jpatest.member.MemberRepository;

@RunWith(Arquillian.class)
public class MemberServicesTest {

	@Deployment
	public static WebArchive createWarDeployment() {

		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importDependencies(ScopeType.TEST, ScopeType.COMPILE)
				.resolve().withTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(Member.class, MemberRepository.class, MemberDAO.class, RepositoryProducer.class)
				.addAsWebInfResource("beans.xml").addAsResource("META-INF/persistence.xml").addAsResource("import.sql")
				.addAsManifestResource("hibernate5-quickstart-ds.xml").addAsManifestResource("MANIFEST.MF")
				.addAsLibraries(files);
		// System.out.println(war.toString(true));
		return war;
	}

	@Inject
	MemberDAO memberDao;

	@Test
	public void should_find_by_email() {
		List<Member> result = memberDao.findByEmail("johndoe@example.com");
		assertEquals(1, result.size());
	}

}
