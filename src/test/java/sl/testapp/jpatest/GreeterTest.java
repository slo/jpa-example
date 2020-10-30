package sl.testapp.jpatest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import sl.testapp.jpatest.member.Member;
import sl.testapp.jpatest.member.MemberDAO;
import sl.testapp.jpatest.member.MemberRepository;

@RunWith(Arquillian.class)
public class GreeterTest {


	@Deployment
	public static WebArchive createWarDeployment() {

		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importDependencies(ScopeType.TEST, ScopeType.COMPILE)
				.resolve()
				.withTransitivity()
				.asFile();

		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(Greeter.class,
				PhraseBuilder.class, Member.class, MemberRepository.class, MemberDAO.class, RepositoryProducer.class);

		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(Greeter.class, PhraseBuilder.class, Member.class, MemberRepository.class, MemberDAO.class)
				.addAsWebInfResource("beans.xml").addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource("import.sql").addAsManifestResource("hibernate5-quickstart-ds.xml")
				.addAsLibrary(jar).addAsManifestResource("MANIFEST.MF").addAsLibraries(files);
		System.out.println(war.toString(true));
		return war;
	}

//	public static WebArchive createDeployment() {
//		WebArchive war = ShrinkWrap.create(WebArchive.class)
//				.addClasses(Greeter.class, PhraseBuilder.class, Member.class, MemberRepository.class, MemberDAO.class)
//				.addAsWebInfResource("beans.xml")
//				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//				.addAsManifestResource("import.sql")
//				.addAsManifestResource("hibernate5-quickstart-ds.xml");
//		System.out.println(war.toString(true));
//		return war;
//	}

	@Inject
	Greeter greeter;

	@Inject
	MemberDAO memberDao;

	@Test
	public void should_create_greeting() {
		assertEquals("Hello, Earthling!", greeter.createGreeting("Earthling"));
		greeter.greet(System.out, "Earthling!");
	}

	@Test
	@Ignore
	public void should_fetch_all_data() {
		List<Member> result = memberDao.findAll();
		assertEquals(3, result.size());

	}

}
