package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

	@Repository
	public interface UserRepository2 extends JpaRepository<User2, Long> {
		@Query(value = "SELECT * FROM userinf WHERE mailadd LIKE %:freeWord%",nativeQuery = true)
			Page<User2> findAllbyfreeword(@Param("freeWord") String Logind,Pageable pageable);

}
