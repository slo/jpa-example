package sl.testapp.jpatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import sl.testapp.jpatest.member.Member;
import sl.testapp.jpatest.member.MemberDAO;

@RunWith(Arquillian.class)
public class MemberServicesIT {

	@Deployment
	public static WebArchive createWarDeployment() {

		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importDependencies(ScopeType.TEST, ScopeType.COMPILE)
				.resolve().withTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "arquillian-jpatest.war")
				.addPackages(true, RepositoryProducer.class.getPackage())
				.addAsWebInfResource("beans.xml")
				.addAsResource("META-INF/persistence.xml").addAsResource("import.sql")
				.addAsManifestResource("hibernate5-quickstart-ds.xml").addAsManifestResource("MANIFEST.MF")
				.addAsLibraries(files);
		System.out.println(war.toString(true));
		return war;
	}

	@Inject
	MemberDAO memberDao;

	// this test is designed to fail if problem is worked around
	@Test
	// @Ignore
	public void should_find_by_email() {
		try {
			List<Member> result = memberDao.findByEmail("johndoe@example.com");
			assertEquals(1, result.size());
			fail();
		} catch (PersistenceException pe) {
		}

	}

	@Test
	@Ignore
	public void verifyThatExceptionThrownPerformsRollback() {

		try {
			memberDao.performManyOperationsAndOptionallyThrowExc("asdfasdf@example.com", true);
			fail();
		} catch (Exception e) {
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			e.printStackTrace();
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			if (!e.getMessage().contains("maj mesydz")) {
				fail();
			}
		}
		List<Member> result2 = memberDao.findAll();
		assertEquals(3, result2.size());
	}

	@Test
	@Ignore
	public void verifyThatSavingChangesDBState() {

		try {
			List<Member> result1 = memberDao.performManyOperationsAndOptionallyThrowExc("asdfasdf@example.com", false);
			assertEquals(4, result1.size());
		} catch (Exception e) {
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			e.printStackTrace();
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBb");
			fail();
		}
		List<Member> result2 = memberDao.findAll();
		assertEquals(4, result2.size());

	}

}
