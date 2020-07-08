package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="sales_clientname")

public class User3 {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
    private String clientname;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getClientname() {
        return clientname;
    }
    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

}
