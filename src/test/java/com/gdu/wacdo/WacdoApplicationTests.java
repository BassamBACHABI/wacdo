package com.gdu.wacdo;

import com.gdu.wacdo.fixtures.DataFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class WacdoApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}

	@Test
	void dataFixtureEstDesactiveeAvecLeProfilTest() {
		assertTrue(applicationContext.getBeansOfType(DataFixture.class).isEmpty());
	}

}
