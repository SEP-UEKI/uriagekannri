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
	@Size(max = 10, message = "顧客名は10桁以内で入力してください")
	private String clientname;

	@NotBlank(message = "シリアルナンバーを入力してください")
	@Size(max = 15, message = "シリアルナンバーは15桁以内で入力してください")
	private String snumber;

	@NotBlank(message = "件名を入力してください")
	@Size(max = 50, message = "件名は50桁以内で入力してください")
	private String subject;


}
