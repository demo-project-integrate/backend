package com.ims;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GatewayServiceApplicationTest {

	@Test
	void applicationStartsSuccessfully() {
		assertThat(true).isTrue();
	}
}
