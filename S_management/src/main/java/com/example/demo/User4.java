package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="sales_status")
public class User4 {

	@EmbeddedId
	private User4PK user4PK;

	private String status;

	/**public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }*/
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
   /** public long getBranchnum() {
        return branchnum;
    }
    public void setBranchnum(long branchnum) {
        this.branchnum = branchnum;
    }*/

}
