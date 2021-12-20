package de.raphaelkunis.springbootmysql.demo;

// import org.junit.Test;	outdated in current Spring Boot version -> use jupiter version below instead
import de.raphaelkunis.springbootmysql.demo.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
//@Disabled
class DemoSpringBootTest {

	@Autowired
	private DemoService demo;

	@Test
	public void contextLoads()  {
		assertTrue(true);
	}

	@Test
	public void testBeanInstantiation() throws Exception {
		assertNotNull(demo);
	}

	@Test
	public void testDemoService() {
		String result = demo.sayHello();
		assertEquals("Hello", result);
	}
}
