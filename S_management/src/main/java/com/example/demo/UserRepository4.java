package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

	@Repository
	public interface UserRepository4 extends JpaRepository<User4, Long>,JpaSpecificationExecutor<User> {

}
