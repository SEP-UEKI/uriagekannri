package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Table(name="sales_status")

public class User4PK {

	private long id;
	private long branchnum;

	public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getBranchnum() {
        return branchnum;
    }
    public void setBranchnum(long branchnum) {
        this.branchnum = branchnum;
    }


}
