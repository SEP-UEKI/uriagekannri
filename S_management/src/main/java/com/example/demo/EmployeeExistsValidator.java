package com.example.demo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeExistsValidator implements ConstraintValidator<EmployeeExists, Object> {

	@Autowired
    UserService userService;

	 private String message;
	 private int id;
	 private String status;

	 @Override
	    public void initialize(EmployeeExists annotation) {
	        this.message = annotation.message();
	        this.id = annotation.id();
	        this.status = annotation.status();
	    }

	 @Override
	    public boolean isValid(Object value, ConstraintValidatorContext context) {
	        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
	        int idValue = (int)beanWrapper.getPropertyValue(id);
	        String statusValue = (String)beanWrapper.getPropertyValue(status);


	        // ステータスが入力されていない場合はチェックしない（フォームクラスのNotNullでチェックする）
	        if(status == null) {
	            return true;
	        }
	        // id,ステータスを検索
	        User4 user4 = userService.findEmployee(idValue, statusValue);
	        // 社員が存在する場合はチェックOK、存在しない場合はチェックNGとする
	        if(user4 != null) {
	            return true;
	        } else {
	            context.disableDefaultConstraintViolation();
	            context.buildConstraintViolationWithTemplate(message).addPropertyNode(status).addConstraintViolation();
	            return false;
	        }

	    }

}
