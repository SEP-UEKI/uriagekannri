package com.example.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.Data;

@Data
@Embeddable

public class User4PK implements Serializable {

	@Id
    @Column(name = "id")
    private int id;

	@Id
    @Column(name = "branchnum")
    private long branchnum;

	@Column(name = "status")
    private String status;



}
