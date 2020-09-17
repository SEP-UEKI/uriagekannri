package com.example.demo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


/**
* ユーザー情報 リクエストデータ
*/
@Data
public class UserRequest4 implements Serializable {

	@NotBlank(message = "ステータスを入力してください")
	@Size(max = 7, message = "ステータスは7桁以内で入力してください")
	private String status;

}