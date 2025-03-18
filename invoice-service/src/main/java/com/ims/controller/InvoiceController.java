package com.ims.controller;

import com.ims.InvoiceService;
import com.ims.dto.InvoiceRequest;
import com.ims.dto.InvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody InvoiceRequest request) {
        System.out.println("post mapping");
        return ResponseEntity.ok(invoiceService.createInvoice(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getInvoice(@PathVariable("id") Long id) {
        System.out.println("by id called"+id);
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getAllInvoices() {
        System.out.println("getall called");
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> deleteInvoiceItem(@PathVariable("itemId") Long itemId) {
        System.out.println("Delete called"+itemId);
        invoiceService.deleteInvoiceItem(itemId);
        return ResponseEntity.ok("Invoice item deleted successfully.");
    }


}

