package com.example.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Embeddable

public class User4PK implements Serializable {

	@Id
    @Column(name = "id")
    private long id;

	@Id
    @Column(name = "branchnum")
    private long branchnum;

	@Column(name = "status")
    private String status;



}
