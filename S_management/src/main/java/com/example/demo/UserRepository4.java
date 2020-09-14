package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

	@Repository
	public interface UserRepository4 extends JpaRepository<User4, Long>,JpaSpecificationExecutor<User> {
		@Query(value = "SELECT * FROM sales_status WHERE id = :freeWord",nativeQuery = true)
		List<User4> findAllByFreeWord(@Param("freeWord") int id);

		@Query(value = "SELECT * FROM sales_status WHERE status = :Freeword AND id = :FreeWord",nativeQuery = true)
		List<User4> findAllfreeword(@Param("Freeword") String status,@Param("FreeWord") int id);
}
