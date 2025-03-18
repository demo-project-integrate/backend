package com.ims;

import com.ims.dto.InvoiceRequest;
import com.ims.dto.InvoiceResponse;
import com.ims.model.Invoice;
import com.ims.model.InvoiceItem;
import com.ims.model.UserDetail;
import com.ims.repository.InvoiceRepository;
import com.ims.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    public InvoiceResponse createInvoice(InvoiceRequest request) {
        // Create User
        UserDetail user = UserDetail.builder()
                .name(request.getUser().getName())
                .email(request.getUser().getEmail())
                .phone(request.getUser().getPhone())
                .build();

        // Create Invoice Items
//        List<InvoiceItem> items = request.getProducts().stream()
//                .map(product -> InvoiceItem.builder()
//                        .productId(product.getProductId())
//                        .productName(product.getProductName())
//                        .quantity(product.getQuantity())
//                        .price(product.getPrice())
//                        .build())
//                .collect(Collectors.toList());
        List<InvoiceItem> items = request.getProducts().stream()
                .map(product -> InvoiceItem.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());

        // Create Invoice
        Invoice invoice = Invoice.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .items(items)
                .userDetail(user)
                .build();

        items.forEach(item -> item.setInvoice(invoice)); // Set invoice reference in items

        invoiceRepository.save(invoice);
        log.info("Invoice created: {}", invoice.getInvoiceNumber());

        return new InvoiceResponse(invoice.getId(), invoice.getInvoiceNumber(), invoice.getCreatedAt(), items, user);
    }
    public InvoiceResponse getInvoiceById(Long id) {
        System.out.println("getInvoiceby id called in service"+id);
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));

        return mapToResponse(invoice);
    }

    public List<InvoiceResponse> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        System.out.println("list"+invoices);
        return invoices.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private InvoiceResponse mapToResponse(Invoice invoice) {
        return new InvoiceResponse(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                invoice.getCreatedAt(),
                invoice.getItems(),
                invoice.getUserDetail()
        );
    }
    public void deleteInvoiceItem(Long itemId) {
        System.out.println("Delete called in service "+itemId);
        Invoice item = invoiceRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Invoice item not found with ID: " + itemId));

        invoiceRepository.delete(item);
        log.info("Invoice item deleted with ID: {}", itemId);
    }

}

