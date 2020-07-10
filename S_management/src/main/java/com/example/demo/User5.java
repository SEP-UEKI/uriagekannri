package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="sales_status2")

public class User5 {

	@Id
    @Column(name = "status")
    private String status;

}
