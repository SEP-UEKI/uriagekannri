package com.example.demo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


/**
* ユーザー情報 リクエストデータ
*/
@Data
public class UserRequest3 implements Serializable {

	@NotBlank(message = "ユーザー名を入力してください")
	@Size(max = 10, message = "ユーザー名は10桁以内で入力してください")
	private String name;

	@NotBlank(message = "メールアドレスを入力してください")
	@Size(max = 20, message = "メールアドレスは20桁以内で入力してください")
	@Email(message = "「@」を含む20桁以内のメールアドレスの形式で入力してください")
	private String mailadd;


	//@NotBlank(message = "ユーザー権限を選択してください")
	private int adomin;
}