////package com.ims;
////
////import com.ims.controller.ProductController;
////import com.ims.service.ProductService;
////import org.junit.jupiter.api.Test;
////import org.springframework.boot.test.context.SpringBootTest;
////import org.springframework.context.annotation.Import;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.MockitoAnnotations;
////import org.springframework.http.MediaType;
////import org.springframework.test.web.servlet.MockMvc;
////import org.springframework.test.web.servlet.setup.MockMvcBuilders;
////
////import java.math.BigDecimal;
////import java.util.Arrays;
////import java.util.List;
////
////import static org.mockito.Mockito.*;
////import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////import com.fasterxml.jackson.databind.ObjectMapper;
////
////
////
////@Import(TestcontainersConfiguration.class)
////@SpringBootTest
////class ProductServiceApplicationTests {
////
////	@Test
////	void ProductControllerTest() {
////	}
////	private MockMvc mockMvc;
////
////	@Mock
////	private ProductService productService;
////
////	@InjectMocks
////	private ProductController productController;
////
////	private ObjectMapper objectMapper = new ObjectMapper();
////
////	@BeforeEach
////	void setUp() {
////		MockitoAnnotations.openMocks(this);
////		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
////	}
////
////
////
////
////}
//
//package com.ims.service;
//import com.ims.dto.ProductRequest;
//import com.ims.dto.ProductResponse;
//import com.ims.model.Product;
//import com.ims.repository.ProductRepository;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
//import static org.hamcrest.Matchers.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//public class ProductServiceTest {
//
//	@Autowired
//	private ProductService productService;
//
//	@MockBean
//	private ProductRepository productRepository;
//
//	private Product mockProduct;
//
//	@BeforeEach
//	void setup() {
//		mockProduct = new Product(1L, "Laptop", "High-end gaming laptop", "1234", new BigDecimal("1500.00"));
//	}
//
//	@Test
//	void testCreateProduct() {
//		ProductRequest request = new ProductRequest("Laptop", "High-end gaming laptop", "1234", new BigDecimal("1500.00"));
//
//		when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
//
//		RestAssured.given()
//				.contentType(ContentType.JSON)
//				.body(request)
//				.when()
//				.post("/api/products")
//				.then()
//				.statusCode(HttpStatus.OK.value())
//				.body("name", equalTo("Laptop"))
//				.body("description", equalTo("High-end gaming laptop"))
//				.body("hsnCode", equalTo("1234"))
//				.body("price", equalTo(1500.00F));
//
//		verify(productRepository, times(1)).save(any(Product.class));
//	}
//
//	@Test
//	void testGetAllProducts() {
//		when(productRepository.findAll()).thenReturn(List.of(mockProduct));
//
//		RestAssured.given()
//				.when()
//				.get("/api/products")
//				.then()
//				.statusCode(HttpStatus.OK.value())
//				.body("$.size()", equalTo(1))
//				.body("[0].name", equalTo("Laptop"))
//				.body("[0].description", equalTo("High-end gaming laptop"));
//
//		verify(productRepository, times(1)).findAll();
//	}
//
//	@Test
//	void testGetProductById() {
//		when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
//
//		RestAssured.given()
//				.when()
//				.get("/api/products/1")
//				.then()
//				.statusCode(HttpStatus.OK.value())
//				.body("name", equalTo("Laptop"))
//				.body("description", equalTo("High-end gaming laptop"));
//
//		verify(productRepository, times(1)).findById(1L);
//	}
//
//	@Test
//	void testGetProductById_NotFound() {
//		when(productRepository.findById(99L)).thenReturn(Optional.empty());
//
//		RestAssured.given()
//				.when()
//				.get("/api/products/99")
//				.then()
//				.statusCode(HttpStatus.NOT_FOUND.value());
//
//		verify(productRepository, times(1)).findById(99L);
//	}
//
//	@Test
//	void testDeleteProduct() {
//		when(productRepository.existsById(1L)).thenReturn(true);
//		doNothing().when(productRepository).deleteById(1L);
//
//		RestAssured.given()
//				.when()
//				.delete("/api/products/1")
//				.then()
//				.statusCode(HttpStatus.NO_CONTENT.value());
//
//		verify(productRepository, times(1)).existsById(1L);
//		verify(productRepository, times(1)).deleteById(1L);
//	}
//
//	@Test
//	void testDeleteProduct_NotFound() {
//		when(productRepository.existsById(99L)).thenReturn(false);
//
//		RestAssured.given()
//				.when()
//				.delete("/api/products/99")
//				.then()
//				.statusCode(HttpStatus.NOT_FOUND.value());
//
//		verify(productRepository, times(1)).existsById(99L);
//		verify(productRepository, times(0)).deleteById(anyLong());
//	}
//}
//
