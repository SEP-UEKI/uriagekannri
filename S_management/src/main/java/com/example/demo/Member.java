package com.example.demo;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@Table(name="sales_management")

@JsonPropertyOrder({"id", "顧客名", "受注日", "シリアル番号", "件名", "数量", "納入指定日", "納入日", "請求日", "見積金額", "受注金額", "ステータス"})
@Data
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	  @JsonProperty("id")
	    private Long id;

	  @JsonProperty("顧客名")
	    private String clientname;

	  @JsonProperty("受注日")
	    private String day;

	  @JsonProperty("シリアル番号")
	    private String snumber;

	  @JsonProperty("件名")
	    private String subject;

	  @JsonProperty("数量")
	    private String quantity;

	  @JsonProperty("納入指定日")
	    private String deliveryday;

	  @JsonProperty("納入日")
	    private String deliveryday2;

	  @JsonProperty("請求日")
	    private String billingday;

	  @JsonProperty("見積金額")
	    private String money;

	  @JsonProperty("受注金額")
	    private String money2;

	  @JsonProperty("ステータス")
	    private String status;


	  public Member() {}
	  public Member(Long id, String clientname, String day, String snumber,String subject,String quantity,
			  String deliveryday,String deliveryday2,String billingday,String money,String money2,String status) {

		  this.id = id;
		  this.clientname = clientname;
		  this.day = day;
		  this.snumber = snumber;
		  this.subject = subject;
		  this.quantity = quantity;
		  this.deliveryday = deliveryday;
		  this.deliveryday2 = deliveryday2;
		  this.billingday = billingday;
		  this.money = money;
		  this.money2 = money2;
		  this.status = status;
	  }

}
