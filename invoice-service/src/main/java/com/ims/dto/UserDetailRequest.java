package com.ims.dto;

import lombok.Data;

@Data
public class UserDetailRequest {
    private String name;
    private String email;
    private String phone;
}

