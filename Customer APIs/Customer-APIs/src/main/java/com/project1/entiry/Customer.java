package com.project1.entiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client;
    private String name;
    private String email;
    private String phone_no;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_date;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_modified_date;
    @Column(unique = true)
    private String customer_code;
    private boolean enabled;
}
