package com.ims;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ims.dto.InvoiceRequest;
import com.ims.dto.InvoiceResponse;
import com.ims.model.Invoice;
import com.ims.model.InvoiceItem;
import com.ims.model.UserDetail;
import com.ims.repository.InvoiceRepository;
import com.ims.repository.UserRepository;
import com.ims.service.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

	@Mock
	private InvoiceRepository invoiceRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private InvoiceService invoiceService;

	private Invoice invoice;
	private UserDetail userDetail;
	private InvoiceItem invoiceItem;

	@BeforeEach
	void setUp() {
		userDetail = UserDetail.builder()
				.name("John Doe")
				.email("john.doe@example.com")
				.phone("1234567890")
				.build();

		invoiceItem = InvoiceItem.builder()
				.productId(1L)
				.productName("Laptop")
				.quantity(1)
				.price(1000.0)
				.build();

		invoice = Invoice.builder()
				.id(1L)
				.invoiceNumber(UUID.randomUUID().toString())
				.createdAt(LocalDateTime.now())
				.userDetail(userDetail)
				.items(Arrays.asList(invoiceItem))
				.build();
	}

	@Test
	void shouldReturnInvoiceById() {
		when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));

		InvoiceResponse response = invoiceService.getInvoiceById(1L);

		assertNotNull(response);
		assertEquals(1L, response.getId());
		assertEquals("John Doe", response.getUser().getName());
	}

	@Test
	void shouldReturnAllInvoices() {
		when(invoiceRepository.findAll()).thenReturn(Arrays.asList(invoice));

		List<InvoiceResponse> responses = invoiceService.getAllInvoices();

		assertFalse(responses.isEmpty());
		assertEquals(1, responses.size());
		assertEquals("John Doe", responses.get(0).getUser().getName());
	}

	@Test
	void shouldDeleteInvoiceById() {
		when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));
		doNothing().when(invoiceRepository).delete(invoice);

		assertDoesNotThrow(() -> invoiceService.deleteInvoiceItem(1L));
		verify(invoiceRepository, times(1)).delete(invoice);
	}
}















//
//
//package com.ims;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//
//@Import(TestcontainersConfiguration.class)
//@SpringBootTest
//class InvoiceServiceTest {
//
//	@Test
//	void contextLoads() {
//	}
//
//}
