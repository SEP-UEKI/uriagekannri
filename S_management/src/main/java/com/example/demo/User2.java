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
    private int delete_flg;
    private int adomin;

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
    public void setMailadd(String mailadd) {
        this.mailadd = mailadd;
    }
    public int getDelete_flg() {
        return delete_flg;
    }
    public void setDelete_flg(int delete_flg) {
        this.delete_flg = delete_flg;
    }
    public int getAdomin() {
        return adomin;
    }
    public void setAdomin(int adomin) {
        this.adomin = adomin;
    }
}
