package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

	@Repository
	public interface UserRepository3 extends JpaRepository<User3, Long>,JpaSpecificationExecutor<User> {

		@Query(value = "SELECT * FROM sales_clientname",nativeQuery = true)
		Page<User3> findAll(Pageable pageable);

}
