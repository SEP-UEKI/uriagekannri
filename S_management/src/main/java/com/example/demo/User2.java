package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="userinf")

public class User2 {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
    private String name;
    private String mailadd;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailadd() {
        return mailadd;
    }
    public void setmailadd(String mailadd) {
        this.mailadd = mailadd;
    }
}
