package com.example.demo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


/**
* ユーザー情報 リクエストデータ
*/
@Data
public class UserRequest implements Serializable {

	@NotBlank(message = "日付を入力してください")
	private String day;

	@NotBlank(message = "顧客名を入力してください")
	@Size(max = 15, message = "顧客名は15桁以内で入力してください")
	private String clientname;

	@NotBlank(message = "シリアルナンバーを入力してください")
	@Size(max = 15, message = "シリアルナンバーは「S-」から始まる15桁以内で入力してください")
	private String snumber;

	@NotBlank(message = "件名を入力してください")
	@Size(max = 50, message = "件名は50桁以内で入力してください")
	private String subject;


	@NotBlank(message = "数量を入力してください")
	@Size(max = 4, message = "数量は4桁以内で入力してください")
	private String quantity;

	@NotBlank(message = "ステータスを入力してください")
	private String status;

	@Size(max = 10, message = "納入指定日は10桁以内で入力してください")
	private String deliveryday;

	@Size(max = 10, message = "納入日は10桁以内で入力してください")
	private String deliveryday2;

	@Size(max = 10, message = "請求日は10桁以内で入力してください")
	private String billingday;

	@Size(max = 10, message = "見積金額は10桁以内で入力してください")
	private String money;

	@Size(max = 11, message = "受注金額は11桁以内で入力してください")
	private String money2;

}
