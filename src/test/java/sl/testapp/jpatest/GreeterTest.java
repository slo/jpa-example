package sl.testapp.jpatest;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import sl.testapp.jpatest.member.Member;
import sl.testapp.jpatest.member.MemberRepository;

@RunWith(Arquillian.class)
public class GreeterTest {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(Greeter.class, PhraseBuilder.class, Member.class, MemberRepository.class)
				.addAsWebInfResource("beans.xml")
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource("import.sql")
				.addAsManifestResource("hibernate5-quickstart-ds.xml");
		System.out.println(war.toString(true));
		return war;
	}

	@Inject
	Greeter greeter;

	@Test
	public void should_create_greeting() {
		assertEquals("Hello, Earthling!", greeter.createGreeting("Earthling"));
		greeter.greet(System.out, "Earthling!");
	}

}
