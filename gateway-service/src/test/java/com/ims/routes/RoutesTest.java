////package com.ims.routes;
////
////import org.junit.jupiter.api.Test;
////import org.junit.jupiter.api.extension.ExtendWith;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
////import org.springframework.boot.test.mock.mockito.MockBean;
////import org.springframework.test.context.junit.jupiter.SpringExtension;
////import org.springframework.test.web.servlet.MockMvc;
////import org.springframework.beans.factory.annotation.Autowired;
////
////import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////
////@ExtendWith(SpringExtension.class)
////@WebMvcTest(Routes.class)
////class RoutesTest {
////
////    @Autowired
////    private MockMvc mockMvc;
////
////    @MockBean
////    private Routes routes;
////
////    @Value("${spring.product.port}")
////    private String productPort;
////
////    @Value("${spring.invoice.port}")
////    private String invoicePort;
////
////    @Test
////    void shouldRouteToProductService() throws Exception {
////        mockMvc.perform(get("/api/product"))
////                .andExpect(status().is3xxRedirection());
////    }
////
////    @Test
////    void shouldRouteToInvoiceService() throws Exception {
////        mockMvc.perform(get("/api/invoices"))
////                .andExpect(status().is3xxRedirection());
////    }
////}
////
//
//
//package com.ims.routes;
//
//import com.ims.config.TestSecurityConfig;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import org.springframework.context.annotation.Import;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.assertj.core.api.Assertions.assertThat;
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Import(TestSecurityConfig.class) // Import the test security configuration
//class RoutesTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    void shouldRouteToProductService() {
//        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/product", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
//    }
//
//    @Test
//    void shouldRouteToInvoiceService() {
//        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/invoices", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
//    }
//}
