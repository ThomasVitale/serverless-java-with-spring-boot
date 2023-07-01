package com.thomasvitale.javafunction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestStreamFunctionApplication.class)
class StreamFunctionApplicationTests {

	@Test
	void contextLoads() {
	}

}
