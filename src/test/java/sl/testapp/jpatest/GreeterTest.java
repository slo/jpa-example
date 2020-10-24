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

@RunWith(Arquillian.class)
public class GreeterTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(Greeter.class).addAsManifestResource(EmptyAsset.INSTANCE,
				"beans.xml");
	}

	@Inject
	Greeter greeter;

	@Test
	public void should_create_greeting() {
		assertEquals("Hello, Earthling!", greeter.createGreeting("Earthling"));
		greeter.greet(System.out, "Earthling!");
	}

}
