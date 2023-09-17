package com.project1.payload;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private int id;
    private String client;
    private String name;
    private String email;
    private String phone_no;
    private Date created_date;
    private Date last_modified_date;

    private String customer_code;
    private boolean enabled;
}
