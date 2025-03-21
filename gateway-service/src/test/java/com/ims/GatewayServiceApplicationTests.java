package com.ims;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TestConfig.class})
@ContextConfiguration(classes = GatewayServiceApplication.class)
class GatewayServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
