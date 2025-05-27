package com.freshworks.accountservicedemo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountResponse {
    private String id;
    private String organisationId;
    private String productId;
    private String name;
    private String domain;
    private String status;

}
