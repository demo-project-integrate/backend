package com.ims.dto;

import com.ims.model.InvoiceItem;
import com.ims.model.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class InvoiceResponse {
    private Long id;
    private String invoiceNumber;
    private LocalDateTime createdAt;
    private List<InvoiceItem> items;
    private UserDetail user;
}

