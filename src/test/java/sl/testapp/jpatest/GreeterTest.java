package sl.testapp.jpatest;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import sl.testapp.jpatest.model.Member;

@RunWith(Arquillian.class)
public class GreeterTest {

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
				.addClasses(Greeter.class, PhraseBuilder.class, Member.class)
				.addAsManifestResource(EmptyAsset.INSTANCE,
						"beans.xml")
				.addAsManifestResource("META-INF/persistence.xml").addAsResource("import.sql")
				.addAsManifestResource("hibernate5-quickstart-ds.xml");
		System.out.println(jar.toString(true));
		return jar;
	}

	@Inject
	Greeter greeter;

	@Test
	public void should_create_greeting() {
		assertEquals("Hello, Earthling!", greeter.createGreeting("Earthling"));
		greeter.greet(System.out, "Earthling!");
	}

}
