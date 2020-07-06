package com.example.demo;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecs {

	private CustomerSpecs() {
    }

	 public static Specification<User> subjectEquals(String Searchinf) {
	        return Searchinf == null ? null : (root, query, builder) ->
	                builder.like(root.get("subject"), "%" + Searchinf + "%");
	    }

	    public static Specification<User> clientnameEquals(String Gclientname) {
	        return Gclientname == null ? null : (root, query, builder) ->
	                builder.equal(root.get("clientname"), Gclientname);
	    }

	    public static Specification<User> statusEquals(String Gstatus) {
	        return Gstatus == null ? null : (root, query, builder) ->
	                builder.equal(root.get("status"), Gstatus);
	    }

	    public static Specification<User> MailInfEquals(String MailInf) {
	        return MailInf == null ? null : (root, query, builder) ->
	                builder.equal(root.get("mailadd"), MailInf);
	    }

	    public static Specification<User> deleteflgEquals(Integer delete_flg) {
	        return delete_flg == null ? null : (root, query, builder) ->
	                builder.equal(root.get("delete_flg"), 0);
	    }


}
