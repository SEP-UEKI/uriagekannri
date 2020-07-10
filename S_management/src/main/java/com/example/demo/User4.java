package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@IdClass(User4PK.class)
@Table(name="sales_status")
public class User4 {

	@Id
    @Column(name = "id")
    private long id;

	@Id
    @Column(name = "branchnum")
    private long branchnum;

	@Column(name = "status")
    private String status;

}
